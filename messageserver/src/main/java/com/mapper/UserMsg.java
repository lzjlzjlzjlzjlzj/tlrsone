package com.mapper;

import java.util.List;
import java.util.Map;

public interface UserMsg {
    //存入消息
    int AddMessage(Map m);

    //查询消息
    List<Map> FindMessage(int fromid);

    //查出对每个用户的最后一条消息
    List<Map> FindLastMessage(int userid);

    //查询messagehistory是否有该用户
    int FindMessagehistroy(int userid);

    //保存第一次用户的messagehistroy
    int AddMessagehistroy(Map m);

    //更新已有用户的messagehistroy
    int UpdateMessagehistroy(String m);

    //查询用户的messagehistory
    String FindMessagehistory(int userid);

    //查询usermessagexq是否有该用户对另一个用户的聊天记录
    int FindUsermessagexq(Map m);

    //保存第一次聊天记录
    int AddUsermessagexq(Map m);

    //更新聊天记录
    int UpdateUsermessagexq(Map m);

    //查询对用户户的聊天记录详情
    String Usermessagexq(Map m);
}
