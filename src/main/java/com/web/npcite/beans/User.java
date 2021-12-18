package com.web.npcite.beans;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data  //生成getter,setter
@AllArgsConstructor  //生成全参数构造函数
@NoArgsConstructor
public class User {
    private int id;
    private String name;
    private String password;
    private String email;
    private int balance; //余额，用于测试购买功能
    //用户角色集合
    private List<Role> roles;
}
