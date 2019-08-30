package com.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.SystemMsgServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
@CrossOrigin
@RestController
public class SystemMsgController {
    @Autowired
    SystemMsgServer u;
    public void AddSystem(int touserid,String message,String time){
        Map m=new HashMap();
        m.put("touserid",touserid);
        m.put("message",message);
        m.put("time",time);
        u.AddSystem(m);
    }

    //查询用户未删除的系统信息
    @RequestMapping("findallsys")
    public Map FindAllsys(String userid){
        return u.FindAllsys(userid);
    }

    //发送消息给指定用户
    @RequestMapping("sendtouser")
    public String SendTouser(@RequestBody Map<String,Object> message){
        String touserid=null;
        touserid=(String)message.get("touserid");
        u.AddMessage(message);
        ObjectMapper o=new ObjectMapper();
        try {
            u.SendTouser(touserid,o.writeValueAsString(message));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return "success";
    }
    //查询用户是否在线
    @RequestMapping("finduser")
    public int FindUser(String userid){
        System.out.println("m:");
        return u.FindUser(userid);

    }
}
