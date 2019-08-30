package com.mapper;

import java.util.List;
import java.util.Map;

public interface User {
    //查询账号
    Map FindZh(String name);

    //查询图事
    List<Map> FindCallery(Map m);

    //查询评论
    List<Map> FindComment(int calleyid);

    //查询评论回复
    List<Map> FindCommentback(int commentid);

    //查询图事获赞
    List<Map> FindCalleryZ(int calleryid);

    //查询图事的图片及文字
    List<Map> FindCalleryinfo(int calleryid);

    //查询评论获赞
    List<Map> FindCommentGetnice(int commentid);

    //查询评论回复获赞
    List<Map> FindCommentbackgetnice(int id);

    //根据用户ID查询用户信息
    Map FindUser(int id);

    //查询评论id获取评论获赞数量
    int FindgetNum(int id);

    //查询评论回复id获取评论回复获赞数量
    int FindgetNum2(int id);

    //查询图事获赞数量
    int FindgetN(int id);

    //点赞
    int Dz(Map m);

    //查询个人用户信息
    Map UserInfo(int userid);

    //根据账号搜索用户信息
    Map SerachZh(String zh);

    //保存系统信息
    int AddSystem(Map m);

    //获取原有的系统消息
    Map FindSysmsg(int id);

    //查询用户是否已经接受了第一条系统消息
    int FindUserok(String userid);

    //查询所有接受一次但未删除的系统消息
    List<Map> FindAllsys(String userid);

    //查询所有的表情
    List<Map> FindAllbq();

    //存入消息
    int AddMessage(Map m);

    //存入账号设备信息
    int AddLoginKey(Map m);

    //查询loginkey信息
    String  FindLoginKey(String logkey);
}
