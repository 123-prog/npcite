package com.web.npcite.service;

import com.web.npcite.beans.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleService {
    public List<Role> selectRoleById(int id);
    public int insertUserRole(int user_id,int role_id);
}
