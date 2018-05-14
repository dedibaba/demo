package com.example.controller;

import com.example.mapper.SysUserMapper;
import com.example.pojo.SysUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class test {
    @Autowired
    private SysUserMapper mapper;
    private static final Logger logger = LoggerFactory.getLogger(test.class);

    @ResponseBody
    @RequestMapping(value = "/test8")
    public SysUser test1() {
        SysUser user = mapper.selectByPrimaryKey("afafad");
        return user;
    }

    @RequestMapping(value = "/main")
    public String mianForm() {
        return "freemaker/main";
    }


    @RequestMapping(value = "/admin")
    public String admin() {
        return "freemaker/admin";
    }

    @RequestMapping(value = "/logout")
    public String logout() {

        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "loginform";
    }

    @RequestMapping(value = "/loginform")
    public String mvc(){
        logger.info("==========================有人进入登陆页面================================");
        System.out.println("out输出了信息");
        return "freemaker/login";
    }
    @RequestMapping(value = "/login")
    public String login(String username, String password){
        Subject subject = SecurityUtils.getSubject();
        if(!subject.isAuthenticated()){
            UsernamePasswordToken usernamePasswordToken=new UsernamePasswordToken(username,password);
            subject.login(usernamePasswordToken);
        }
        return "freemaker/main";
    }
}