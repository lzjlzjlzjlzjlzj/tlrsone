package com.service;

import com.WebSocketServer.Msg;
import com.mapper.UserMsg;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import javax.websocket.EncodeException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserMsgServer {
    @Autowired
    Msg c;
    @Resource
    UserMsg userMsg;
    //查询用户是否在聊天界面
    public int FindUser(String userid){
        int i=0;
        Msg c1=c.getWebSocketServer(userid);
        if(c1!=null){
            i=1;
        }
        return i;
    }
    //发送消息给用户
    public void SendTouserid(String userid,String Message){
        Msg c1=c.getWebSocketServer(userid);
        try {
            c1.sendMessage(Message);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (EncodeException e) {
            e.printStackTrace();
        }
    }
    //存入一条消息
    public int AddMessage(Map m){
        return userMsg.AddMessage(m);
    }

    //查出消息
    public  Map FindMessage(int fromid){
        Map m=new HashMap();
        List<Map> l=userMsg.FindMessage(fromid);
        m.put("list",l);
        return m;
    }
    //存进messsgehistroy
    public int AddMessagehistroy(int userid,String message2){
        int i=userMsg.FindMessagehistroy(userid);
        if(i>0){
            userMsg.UpdateMessagehistroy(message2);
        }else{
            Map m=new HashMap();
            m.put("userid",userid);
            m.put("message",message2);
            userMsg.AddMessagehistroy(m);
        }
        return i;
    }
    //查询主界面的messagehistory
    public String FindMessagehistory(int userid){
        return userMsg.FindMessagehistory(userid);
    }

    //存进usermessagexq
    public int AddUsermessagexq(int userid,int touserid,String message2){
        Map m=new HashMap();
        m.put("userid",userid);
        m.put("touserid",touserid);
        m.put("message",message2);
        int i=userMsg.FindUsermessagexq(m);
        if(i>0){
            userMsg.UpdateUsermessagexq(m);
        }else {
            userMsg.AddUsermessagexq(m);
        }
        return i;
    }

    //查询聊天界面的用户聊天记录
    public String Usermessagexq(int userid,int touserid) {
        Map m = new HashMap();
        m.put("userid", userid);
        m.put("touserid", touserid);
        if (userMsg.Usermessagexq(m) != null) {
            return userMsg.Usermessagexq(m);
        } else {
            return "暂无聊天记录";
        }
    }
}
