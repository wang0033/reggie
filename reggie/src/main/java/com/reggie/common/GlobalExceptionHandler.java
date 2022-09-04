package com.reggie.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * 全局异常处理
 * @author 86182
 * @create 2022/8/14 17:45
 */
@ControllerAdvice(annotations = {RestController.class, Controller.class})
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 捕获新增数据出现重复异常
     * @param sqlIntegrityConstraintViolationException
     * @return
     */
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public R<String> exceptionHandler(SQLIntegrityConstraintViolationException sqlIntegrityConstraintViolationException){
        log.error(sqlIntegrityConstraintViolationException.getMessage());
        if (sqlIntegrityConstraintViolationException.getMessage().contains("Duplicate entry")) {
            String[] s = sqlIntegrityConstraintViolationException.getMessage().split(" ");
            return R.error(s[2] + "已存在");
        }
        return R.error("未知错误");
    }

    /**
     * 捕获分类关联菜品或者套餐不能删除异常
     * @param customException
     * @return
     */
    @ExceptionHandler(CustomException.class)
    public R<String> exceptionHandler(CustomException customException){
        log.error(customException.getMessage());
        return R.error(customException.getMessage());
    }
}
