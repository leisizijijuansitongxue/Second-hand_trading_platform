package com.example.secondhand_trading_platform.POJO;

import lombok.Data;

@Data
public class Response {
    Integer Code;
    String Message;
    Object Data;

    public Response(Integer code, String message, Object data) {
        this.Code = code;
        this.Message = message;
        this.Data = data;
    }

    public  void toString2(Response response){
        System.out.println(response.Code);
    }

    //成功后传送给前端的信息
    public static Response success(){
        return new Response(200 , "success" , "");
    }

    public static Response success(Integer code ,String message, Object data){
        return new Response(code , message , data);
    }

    public static Response error(Integer code , String message){
        return new Response(code , message , "");
    }

}
