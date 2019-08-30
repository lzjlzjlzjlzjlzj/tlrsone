package com.service;

import java.util.Map;

public interface Userservice {
    Map FindZh(String name);
    Map FindCallery(int userid, int page);
    //点赞
    int Dz(Map m);
    //查询用户信息
    Map UserInfo(int userid);
    //根据账号搜索用户信息
    Map SerachZh(String zh);
    //保存系统信息
    int AddSystem(Map m);
    //查询所有表情
    Map FindAllbq();
    //存入消息
    int AddMessage(Map m);
}
