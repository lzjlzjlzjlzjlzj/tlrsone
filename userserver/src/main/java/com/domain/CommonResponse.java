package com.domain;

/**
 *
 * @param <t>  公共处理返回类
 */

public class CommonResponse <t> {
    /**
     *  code : 响应状态码
     */
    private  String code;
    private String  msg;
    private  t   data;

    public CommonResponse (String code,String msg,t data){
         this.code=code;
         this.msg=msg;
         this.data=data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public t getData() {
        return data;
    }

    public void setData(t data) {
        this.data = data;
    }

    /**
     * 公共成功调用
     * @param data 响应成功数据
     * @return
     */
    public static <t>CommonResponse  Success( t data){
       return  new CommonResponse <t>("200","响应成功",data);
    }

    /**
     * 公共失败调用
     */
    public static <t>CommonResponse Fail(String code,String msg,t data){
        return  new CommonResponse<t>(code,msg,data);
    }
}
