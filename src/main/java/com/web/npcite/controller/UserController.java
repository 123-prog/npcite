package com.web.npcite.controller;

import com.web.npcite.beans.Commodity;
import com.web.npcite.beans.Result;
import com.web.npcite.beans.ShoppingCart;
import com.web.npcite.beans.User;
import com.web.npcite.service.*;
import lombok.extern.log4j.Log4j2;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping(value = "/user")
@Log4j2
public class UserController {
    @Autowired
    private UserSqlService userSqlService;

    @Autowired
    private CartService cartService;

    @Autowired
    private CommodityService commodityService;

    @Autowired
    private LogService logService;

    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/login",method = {RequestMethod.GET,RequestMethod.POST})
    public Result login(@RequestParam(name = "username") String username, @RequestParam(name = "password") String password){
        Subject subject = SecurityUtils.getSubject();
        try{
            UsernamePasswordToken upToken = new UsernamePasswordToken(username,password);
            subject.login(upToken);
            return new Result(200,"登录成功",null);
        }catch (Exception e) {
            return new Result(500,"用户名或密码错误",null);
        }
    }
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public Result register(@RequestParam(name = "username") String username, @RequestParam(name = "password") String password , @RequestParam(name = "email") String email){
        if(username.isEmpty() || password.isEmpty() || email.isEmpty()){
            return new Result(400,"参数不能为空",null);
        }
        else if(userSqlService.isUserExist(username)!=0){
            return new Result(400,"用户名已存在",null);
        }
        else if(userSqlService.isEmailExist(email)!=0){
            return new Result(400,"邮箱已经被注册",null);
        }
        else {
            userSqlService.insertUser(username, password, email);
            int user_id = userSqlService.findUserByName(username).getId();
            roleService.insertUserRole(user_id,1);  //初始化用户角色为normal_user
            return new Result(200,"注册成功",null);
        }
    }

    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public Result logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return new Result(200,"登出成功",null);
    }

    @RequestMapping(value = "/info",method = RequestMethod.GET)
    public User info(){
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        return user;
    }

    @RequestMapping(value = "/addCommodity")
    public Result add(@RequestParam(name = "id") int id){
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        int res;
        if(commodityService.isCommodityExist(id)!=0){
            if(cartService.isShoppingRecordExist(user.getId(),id)==0){
                String commodity_name = commodityService.getCommodityById(id).getName();
                res = cartService.insertShoppingCart(user.getId(),id,1,commodity_name);
            }
            else{
                int cart_id = cartService.getIdByUserIdAndCommodityID(user.getId(),id);
                ShoppingCart shoppingCart = cartService.getShoppingCartById(cart_id);
                res = cartService.updateShoppingCart(user.getId(),id,shoppingCart.getNum()+1,cart_id);
            }
            if(res!=0){
                Commodity commodity = commodityService.getCommodityById(id);
                Timestamp time= new Timestamp(System.currentTimeMillis());
                logService.insertLog(commodity.getOwner(),String.format("用户（id：%d|name:%s)将商品(id:%d|name:%s)加入了购物车",user.getId(),user.getName(),id,commodity.getName()),time);
                return new Result(200,"添加至购物车成功",null);
            }else{
                return new Result(500,"添加失败",null);
            }
        }
        else{
            return new Result(500,"添加失败，商品不存在",null);
        }

    }

    @RequestMapping(value = "/delCommodity")
    public Result del(@RequestParam(name = "id") int id){
        cartService.deleteShoppingCart(id);
        return new Result(200,"删除成功",null);
    }

    @RequestMapping(value = "/showCart")
    public List<ShoppingCart> show(){
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        return cartService.getShoppingCartsByUserId(user.getId());
    }

    @RequestMapping(value = "/buy")
    public Result buy(@RequestParam(name="commodity_id") int commodity_id,@RequestParam(name = "num") int num){
        Commodity commodity = commodityService.getCommodityById(commodity_id);
        if(commodity.getReserve()<num){
            return new Result(500,"购买失败，库存不足",null);
        }
        else{
            int newReserve = commodity.getReserve()-num;
            User user = (User) SecurityUtils.getSubject().getPrincipal();
            int newBalance = user.getBalance()-commodity.getPrice()*num;
            if(newBalance<0){
                return new Result(500,"购买失败，余额不足",null);
            }
            else{
                //todo 发邮件确定发货
                //扣除余额
                userSqlService.updateUser(user.getId(),user.getName(),user.getPassword(),user.getEmail(),newBalance);
                //扣除商品库存数,增加售出数
                commodityService.updateCommodity(commodity.getName(),commodity.getMsg(),commodity.getPrice(),newReserve,commodity.getOwner(),commodity.getSale_num()+num,commodity.getIncome()+commodity.getPrice()*num,commodity_id);
                //从购物车移除
                int cart_id = cartService.getIdByUserIdAndCommodityID(user.getId(),commodity_id);
                cartService.deleteShoppingCart(cart_id);

                log.info(String.format("用户（id：%d|name:%s)购买了商品(id:%d|name:%s)",user.getId(),user.getName(),commodity.getId(),commodity.getName()));
                Timestamp time= new Timestamp(System.currentTimeMillis());
                logService.insertLog(commodity.getOwner(),String.format("用户（id：%d|name:%s)购买了商品(id:%d|name:%s)",user.getId(),user.getName(),commodity.getId(),commodity.getName()),time);
                return new Result(200,"购买成功，确认邮件已经发出，请尽快确认发货",null);
            }
        }
    }
}
