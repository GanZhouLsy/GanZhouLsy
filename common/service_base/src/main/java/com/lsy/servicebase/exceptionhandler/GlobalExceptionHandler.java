package com.lsy.servicebase.exceptionhandler;

import com.lsy.commonutils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author liusy
 * @Date 2023/8/26 14:24
 * @Description 统一异常处理
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    //指定出现什么异常执行该方法
    @ExceptionHandler(Exception.class)//所有异常
    @ResponseBody //返回数据
    public R error(Exception e) {
        e.printStackTrace();
        return R.error().message("有点小问题，请联系渝哥！");
    }

    //特定异常处理
//    @ExceptionHandler(ArithmeticException.class)

    //自定义异常处理；需要手动抛出
    @ExceptionHandler(GuliException.class)
    @ResponseBody
    public R error(GuliException e) {
        log.error(e.getMessage());//测试日志
        e.printStackTrace();
        return R.error().code(e.getCode()).message(e.getMsg());
    }

}
