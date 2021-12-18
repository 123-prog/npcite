package com.web.npcite.config;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

public class MyCredentialsMatcher extends SimpleCredentialsMatcher {
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info){
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        String pwd1 = new String(upToken.getPassword());
        String pwd2 = (String) info.getCredentials();

        //todo 密码的md5验证
        return super.equals(pwd1,pwd2);
    }
}
