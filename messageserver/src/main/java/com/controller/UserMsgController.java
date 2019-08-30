package com.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.UserMsgServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * 用户信息处理
 * 2019/8/29 17:55
 * lzj
 */
public class UserMsgController {
    @Autowired
    UserMsgServer m;
    //查询用户是否在聊天界面
    @RequestMapping("finduser")
    int FindUser(String userid){
        return m.FindUser(userid);
    }
    //发送消息给用户
    @RequestMapping("sendtouser")
    String SendTouser(@RequestBody Map<String,Object> message){
        String userid= (String) message.get("touserid");
        ObjectMapper o=new ObjectMapper();
        try {
            m.SendTouserid(userid,o.writeValueAsString(message));
        } catch (JsonProcessingException e) {
            return "发送失败";
        }
        return "success";
    }
    //查询聊天消息
    @RequestMapping("findmessage")
    Map FindMessage(int fromid){
        return m.FindMessage(fromid);
    }

    //存入messagehistroy
    @RequestMapping("addmessagehistroy")
    void AddMessagehistroy(int userid,String message){
        m.AddMessagehistroy(userid,message);
    }

    //查询meessagehistory
    @RequestMapping("findmessagehistory")
    String FindMessagehistory(int userid){
        return m.FindMessagehistory(userid);
    }

    //存入用户聊天记录
    @RequestMapping("addusermessagexq")
    void AddUsermessagexq(int userid,int touserid,String message){
        m.AddUsermessagexq(userid,touserid,message);
    }

    //查询用户聊天记录
    @RequestMapping("usermessagexq")
    String Usermessagexq(int userid,int touserid){
        return m.Usermessagexq(userid,touserid);
    }
}
