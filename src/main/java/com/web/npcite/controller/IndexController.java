package com.web.npcite.controller;

import com.web.npcite.beans.Commodity;
import com.web.npcite.beans.User;
import com.web.npcite.service.CommodityService;
import com.web.npcite.service.LogService;
import com.web.npcite.service.SqlService;
import lombok.extern.log4j.Log4j2;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping(value = "/index")
@Log4j2
public class IndexController {
    @Autowired
    private SqlService sqlService;

    @Autowired
    private LogService logService;

    @Autowired
    private CommodityService commodityService;

    @RequestMapping(value = "/",method = {RequestMethod.GET,RequestMethod.POST})
    public String index(){
        return "welcome to npCite!!!";
    }

    @RequestMapping(value = "/show",method = {RequestMethod.GET,RequestMethod.POST})
    public List<Commodity> show(){
        return commodityService.getAllCommodity();
    }

    @RequestMapping(value = "/showDetails",method = {RequestMethod.GET,RequestMethod.POST})
    public Commodity showDetails(@RequestParam(name = "id") int id){
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        Commodity commodity = commodityService.getCommodityById(id);
        log.info(String.format("用户（id：%d|name:%s)打开了商品(id:%d|name:%s)的详细信息进行了浏览",user.getId(),user.getName(),id,commodity.getName()));
        Timestamp time= new Timestamp(System.currentTimeMillis());
        logService.insertLog(user.getId(),String.format("用户（id：%d|name:%s)打开了商品(id:%d|name:%s)的详细信息进行了浏览",user.getId(),user.getName(),id,commodity.getName()),time);
        return commodityService.getCommodityById(id);
    }
}
