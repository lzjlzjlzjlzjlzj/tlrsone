package com.mapper;

import java.util.List;
import java.util.Map;

public interface SystemMsg {
    //保存系统信息
    int AddSystem(Map m);

    //获取原有的系统消息
    Map FindSysmsg(int id);

    //查询用户是否已经接受了第一条系统消息
    int FindUserok(String userid);

    //查询所有接受一次但未删除的系统消息
    List<Map> FindAllsys(String userid);

    //存入消息
    int AddMessage(Map m);
}
