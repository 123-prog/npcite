package com.web.npcite.controller;

import com.web.npcite.beans.Commodity;
import com.web.npcite.beans.User;
import com.web.npcite.service.CommodityService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/shop")
public class CommodityController {

    @Autowired
    private CommodityService commodityService;

    @RequiresRoles("sales_user")
    @RequestMapping(value = "/show",method = {RequestMethod.GET,RequestMethod.POST})
    public List<Commodity> show(){
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        int owner = user.getId();
        return commodityService.getCommodityByOwner(owner);
    }

    @RequiresRoles("sales_user")
    @RequestMapping(value = "/add",method = {RequestMethod.GET,RequestMethod.POST})
    public int add(@RequestParam(name="name") String name,@RequestParam(name="msg") String msg,@RequestParam(name="price") int price,@RequestParam(name="reserve") int reserve){
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        return commodityService.insertCommodity(name,msg,price,reserve,user.getId());
    }

    @RequiresRoles("sales_user")
    @RequestMapping(value = "/del",method = {RequestMethod.GET,RequestMethod.POST})
    public int del(@RequestParam(name = "id") int id){
        return commodityService.deleteCommodity(id);
    }

    @RequiresRoles("sales_user")
    @RequestMapping(value = "/update",method = {RequestMethod.GET,RequestMethod.POST})
    public int update(@RequestParam(name="name") String name,@RequestParam(name="msg") String msg,@RequestParam(name="price") int price,@RequestParam(name="reserve") int reserve,@RequestParam(name="sale_num") int sale_num,@RequestParam(name="income") int income,@RequestParam(name="id") int id){
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        return commodityService.updateCommodity(name,msg,price,reserve,user.getId(),sale_num,income,id);
    }


}
