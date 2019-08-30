package com.controller;

import com.service.Userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.tool.LoginKey;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
public class Controller {
    @Autowired
    Userservice u;
    @Autowired
    LoginKey k;
    @RequestMapping("hello")
    String hello(){
        return "hello";
    }
    //登录验证
    @RequestMapping("findzh")
    Map FindZh(String name,String pwd){
        Map i=u.FindZh(name);
        Map m=new HashMap();
        if(i==null){
            m.put("code","账号不存在");
            return m;
        }else{
            if(i.get("pwd").equals(pwd)) {
                i.put("loginkey", k.getLoginKey(name));
                return i;
            }else{
                m.put("code","密码不正确");
                return m;
            }
        }
    }

    //loginkey登录
    @RequestMapping("loginkey")
    Map LoginKey(String loginkey){
        return k.getUserid(loginkey);
    }

    //获取用户登录地址
    @RequestMapping("location")
    void Loaction(String location){
        System.out.println("用户登录地址："+location);
    }

    //分页查询未浏览的所有图事
    @RequestMapping("findcallery")
    Object FindCallery(int userid,int page){
        System.out.println(page);
        String s=""+userid+"";
        return u.FindCallery(userid,page);
    }

    //点赞
    @RequestMapping("dz")
    void Dz(int userid,int calleryid,String time){
        System.out.println(userid);
        Map m=new HashMap();
        m.put("userid",userid);
        m.put("calleryid",calleryid);
        m.put("time",time);
        u.Dz(m);
    }
    //加载个人用户信息
    @RequestMapping("userinfo")
    Map UserInfo(int userid){
        return u.UserInfo(userid);
    }

    //搜索账号
    @RequestMapping("serachzh")
    Map SerachZh(String zh){
        System.out.println(zh+"账号");
        return u.SerachZh(zh);
    }

    //查询所有表情
    @RequestMapping("findallbq")
    Map FindAllbq(){
        return u.FindAllbq();
    }

    //支付功能
    @RequestMapping("alipay")
    String Alipay(){
        return null;
    }
}
