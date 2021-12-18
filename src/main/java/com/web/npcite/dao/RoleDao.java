package com.web.npcite.dao;

import com.web.npcite.beans.Role;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RoleDao {
    @Select("select role.id,role.roleName from user_role,role where user_role.role_id=role.id and user_id=#{id}")
    public List<Role> selectRoleById(@Param("id") int id);

    @Insert("insert into user_role(user_id,role_id) values(#{user_id},#{role_id})")
    public int insertUserRole(@Param("user_id") int user_id,@Param("role_id") int role_id);
}
