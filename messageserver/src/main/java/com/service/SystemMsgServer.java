package com.service;

import com.WebSocketServer.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mapper.SystemMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.websocket.EncodeException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class SystemMsgServer {
    @Autowired
    User w;
    @Resource
    SystemMsg u;
    //自动一条发送系统信息
    public void  sendSystem(String userid){
        ObjectMapper o=new ObjectMapper();
        Map m=new HashMap();
        Map m2=u.FindSysmsg(1);
        m.put("from","System");
        m.put("message",m2.get("message"));
        m.put("id",m2.get("id"));
        User newW=w.getWebSocketServer(userid);
        try {
            System.out.println("w: "+w);
            if(u.FindUserok(userid)<1) {
                newW.sendMessage(o.writeValueAsString(m));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (EncodeException e) {
            e.printStackTrace();
        }
    }

    //查询未删除的系统信息
    public Map FindAllsys(String userid){
        Map m=new HashMap();
        List<Map> l=u.FindAllsys(userid);
        m.put("list",l);
        return m;
    }

    //发送信息给指定用户
    public void SendTouser(String userid,String message){
        User newW=w.getWebSocketServer(userid);
        try {
            newW.sendMessage(message);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (EncodeException e) {
            e.printStackTrace();
        }
    }

    //查询用户是否在线
    public  int FindUser(String userid){
        int i=0;
        User newW=w.getWebSocketServer(userid);
        if(newW!=null){
            i=1;
        }
        return i;
    }

    /**
     * 存入系统消息
     * @param m
     * @return
     */
    public int AddSystem(Map m) {
        return u.AddSystem(m);
    }

    public int AddMessage(Map m){
        return u.AddMessage(m);
    }
}
