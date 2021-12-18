package com.web.npcite.service;

import com.web.npcite.beans.User;
import org.apache.ibatis.annotations.Param;

public interface UserSqlService {
    public User findUserByName(String username);
    public int isUserExist(String name);
    public int isEmailExist(String name);
    public User findUserById(int id) ;
    public int insertUser(String name, String pwd, String email);
    public int deleteUser(int id) ;
    public int updateUser(int id, String username, String password, String email,int balance) ;
}
