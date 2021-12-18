package com.web.npcite.controller;

import com.web.npcite.beans.Commodity;
import com.web.npcite.beans.User;
import com.web.npcite.beans.log;
import com.web.npcite.service.CommodityService;
import com.web.npcite.service.LogService;
import lombok.extern.log4j.Log4j2;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/sales")
@Log4j2
public class SalesController {
    @Autowired
    private CommodityService commodityService;

    @Autowired
    private LogService logService;

    @RequestMapping(value = "/show")  //销售报表展示
    @RequiresRoles("sales_user")
    public List<Commodity> show(){
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        return commodityService.getCommodityByOwner(user.getId());
    }


    @RequestMapping(value = "/logs")   //商品日志，包含被浏览的记录和被购买的记录
    public List<log> logs(){
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        return logService.selectLogByUserId(user.getId());
    }
}
