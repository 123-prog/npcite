package com.web.npcite.dao;

import com.web.npcite.beans.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserDao {
    @Select("select count(*) from user where name=#{name}")
    public int isUserExist(@Param("name") String name);

    @Select("select count(*) from user where email=#{email}")
    public int isEmailExist(@Param("email") String email);

    @Select("select * from user where name=#{username}")
    public User findUserByName(@Param("username") String username);

    @Select("select * from user where id=#{id}")
    public User findUserById(@Param("id") int id);

    @Insert("insert into user(name,password,email) values(#{username},#{password},#{email})")
    public int insertUser(@Param("username") String name,@Param("password") String pwd,@Param("email") String email);

    @Delete("delete from user where id=#{id}")
    public int deleteUser(@Param("id") int id);

    @Update("update user set name=#{username},password=#{password},email=#{email},balance=#{balance} where id=#{id}")
    public int updateUser(@Param("id") int id,@Param("username") String username,@Param("password") String password,@Param("email") String email,@Param("balance") int balance);
}
