package com.reggie.common;

/**
 * 基于ThreadLocal封装工具类，用于保存和获取当前用户id
 * @author 86182
 * @create 2022/8/20 16:58
 */
public class BaseContext {
    private static ThreadLocal<Long> threadLocal = new ThreadLocal<>();
    public static void setCurrentId(long id){
        threadLocal.set(id);
    }
    public static long getCurrentId(){
        return threadLocal.get();
    }
}
