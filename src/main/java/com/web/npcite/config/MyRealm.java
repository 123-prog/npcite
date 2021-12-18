package com.web.npcite.config;

import com.web.npcite.beans.Role;
import com.web.npcite.beans.User;
import com.web.npcite.service.RoleService;
import com.web.npcite.service.UserSqlService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MyRealm extends AuthorizingRealm {
    @Autowired
    private UserSqlService userSqlService;

    @Autowired
    private RoleService roleService;

    @Override  //授权
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

        User user = (User) principalCollection.getPrimaryPrincipal();
        List<Role> roles = roleService.selectRoleById(user.getId());
        Set<String> s = new HashSet<>();
        for(Role role:roles){
            s.add(role.getRoleName());
        }
        simpleAuthorizationInfo.setRoles(s);
        return simpleAuthorizationInfo;
    }

    @Override  //认证
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();

        if(userSqlService.isUserExist(username)==0){
            return null;
        }
        else{
            User user = userSqlService.findUserByName(username);
            user.setRoles(roleService.selectRoleById(user.getId()));
            return new SimpleAuthenticationInfo(user,user.getPassword(),this.getName());
        }
    }
}
