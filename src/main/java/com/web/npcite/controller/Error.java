package com.web.npcite.controller;

import com.web.npcite.beans.Result;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Error implements ErrorController {
    @RequestMapping(value = "/error")
    public Result error(){
        return new Result(500,"应用发生了错误，可能原因：用户权限不足，路径不存在",null);
    }
}
