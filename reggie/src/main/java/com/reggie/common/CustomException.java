package com.reggie.common;

/**
 * 自定义业务异常
 * @author 86182
 * @create 2022/8/21 16:09
 */
public class CustomException extends RuntimeException{
    public CustomException(String message){
        super(message);
    }
}
