package com.lsy.servicebase.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author liusy
 * @Date 2023/8/26 20:46
 * @Description 自定义异常
 */
@Data
@AllArgsConstructor  //生成有参数构造方法
@NoArgsConstructor   //生成无参数构造
public class GuliException extends RuntimeException {
    private Integer code;//状态码
    private String msg;//异常信息
}
