package com.service.impl;

import com.mapper.User;
import org.springframework.stereotype.Service;
import com.service.Userservice;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class Userserviceimpl implements Userservice {
    @Resource
    private User u;
    @Override
    public Map FindZh(String name) {
        return u.FindZh(name);
    }
    //查询图事以及关联信息
    @Override
    public Map FindCallery(int userid,int page) {
        Map pagem=new HashMap();
        pagem.put("from",(page-1)*5);
        pagem.put("to",page*5);
        pagem.put("userid",userid);
        List<Map> callery=u.FindCallery(pagem);
        //创建返回的Map
        Map backjson=new HashMap();
        //创建一个存储所有信息的集合
        List<Map> all=new ArrayList<>();
        for(int i=0;i<callery.size();i++) {
            //创建callery的Map
            Map<String,Object> m=new HashMap<>();
            int num=u.FindgetN((int)(callery.get(i).get("id")));
            m.put("calleryid",callery.get(i).get("id"));
            m.put("title",callery.get(i).get("title"));
            m.put("time",callery.get(i).get("time"));
            m.put("name",callery.get(i).get("nickname"));
            m.put("num",num);
            System.out.println("用户id"+userid+"加载了"+callery.get(i).get("id") + "" + callery.get(i).get("title") + "" + callery.get(i).get("time"));
            //根据图事ID获取图事相对应图片及文字
            List<Map> calleryinfo=u.FindCalleryinfo((int)(callery.get(i).get("id")));
            m.put("info",calleryinfo);
            for (int j=0;j<calleryinfo.size();j++){

            }
            //创建评论List
            List<Map> L=new ArrayList();
            //根据图事id获取评论
            List<Map> comment=u.FindComment((int)(callery.get(i).get("id")));
            for(int j=0;j<comment.size();j++){
                //创建每条评论的信息Map
                Map message=new HashMap();
                int num2=u.FindgetNum((int)(comment.get(j).get("id")));
                message.put("userid",comment.get(j).get("userid"));
                message.put("nickname",comment.get(j).get("nickname"));
                message.put("content",comment.get(j).get("content"));
                message.put("time",comment.get(j).get("time"));
                message.put("num",num2);
                message.put("looknum",comment.get(j).get("looknum"));
                //创建评论回复的List
                List<Map> back=new ArrayList();
                //根据评论id获取评论回复
                List <Map> commentback=u.FindCommentback((int)(comment.get(j).get("id")));
                for (int x=0;x<commentback.size();x++){
                    //创建每条回复的信息map
                    Map backmessage=new HashMap();
                    int num3=u.FindgetNum2((int)(commentback.get(x).get("id")));
                    String touserid=(String) (u.FindUser((int)(commentback.get(x).get("touserid"))).get("nickname"));
                    backmessage.put("fromuserid",commentback.get(x).get("fromuserid"));
                    backmessage.put("fromnickname",commentback.get(x).get("nickname"));
                    backmessage.put("touserid",commentback.get(x).get("touserid"));
                    backmessage.put("tonickname",touserid);
                    backmessage.put("content",commentback.get(x).get("content"));
                    backmessage.put("num",num3);
                    back.add(backmessage);
                }
                message.put("commentback",back);
                L.add(message);
            }
            m.put("comment",L);
            all.add(m);
        }
        backjson.put("list",all);
        return backjson;
    }
    //给图事点赞
    @Override
    public int Dz(Map m) {
        u.Dz(m);
        return 0;
    }
    //查询用户信息
    @Override
    public Map UserInfo(int userid) {
        //创建前台渲染的用户信息list
        List<Map> l=new ArrayList<>();
        l.add(u.UserInfo(userid));
        //创建响应Map
        Map m=new HashMap();
        m.put("list",l);
        return m;

    }

    //根据账号搜索用户信息
    @Override
    public Map SerachZh(String zh) {
       List<Map> m2=new ArrayList<>();
       Map m1=new HashMap();
       m2.add(u.SerachZh(zh));
        if(u.SerachZh(zh)==null){
            m1.put("message","0");
            return m1;
        }else {
            m1.put("message",1);
            m1.put("list",m2);
            return m1;
        }
    }
    //保存系统信息
    @Override
    public int AddSystem(Map m) {
        return u.AddSystem(m);
    }

    //查询所有表情
    @Override
    public Map FindAllbq() {
        Map m=new HashMap();
        List<Map> l=u.FindAllbq();
        m.put("list",l);
        return  m;
    }

    //存入消息
    public int AddMessage(Map m){
        return u.AddMessage(m);
    }

}
