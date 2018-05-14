package com.example.shiro;

import com.example.mapper.SysUserMapper;
import com.example.pojo.SysUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

public class MyShiroRealm extends AuthorizingRealm {
    @Autowired
    private SysUserMapper sysUserMapper;
     //这里的这个 principalCollection 就是用户唯一标识 （就是用户名）
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        Object primaryPrincipal = principalCollection.getPrimaryPrincipal();
        //获取用户
        Object userId = SecurityUtils.getSubject().getPrincipal();
        SimpleAuthorizationInfo info =  new SimpleAuthorizationInfo();
        //获取用户角色
//        System.out.println(userId.toString());
        Set<String> roleSet = new HashSet<String>();
        if(userId.equals("afafad")) {
            roleSet.add("admin");
        }else{
            throw new UnknownAccountException("你不是管理员");
        }
        info.setRoles(roleSet);
        return info;

    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //取出authenticationToken中的用户名和密码
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        String password = String.valueOf(token.getPassword());
        //将这个用户名和密码进行比对
        SysUser user = sysUserMapper.selectByPrimaryKey(username);
//        System.out.println(password);
//        System.out.println(user.getUserPassword());
        if (user.getUserPassword().equals(password)){

        }else{
            throw new UnknownAccountException("密码错误");
        }
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(username,password,this.getName());
        return  simpleAuthenticationInfo;
    }
}