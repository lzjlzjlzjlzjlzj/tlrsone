package com.tool;

import com.mapper.*;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Component
public class LoginKey {
    @Resource
    User u;
    //给账号自动生成的loginkey
    public String getLoginKey(String zh){
      String loginkey="";
      //生成loginkey
        Random r=new Random();
        String str="qwertyuiopasdfghjklzxcvbnm";
        int i=str.length();
        for (int j=0;j<i;j++){
           int rnum=r.nextInt(25);
            loginkey+=str.charAt(rnum);
        }
        //调用dao层将设备信息及账号存入数据库
        Map m=new HashMap();
        m.put("zh",zh);
        m.put("loginkey",loginkey);
        u.AddLoginKey(m);


      return loginkey;
    }

    //根据loginkey获取用户的userid信息
    public Map getUserid(String loginkey){
        String zh=u.FindLoginKey(loginkey);
        return u.FindZh(zh);
    }


}
