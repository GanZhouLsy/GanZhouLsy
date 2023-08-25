package com.lsy.edu.controller;


import com.lsy.edu.entity.Teacher;
import com.lsy.edu.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author lsy
 * @since 2023-08-25
 */
@RestController
@RequestMapping("/edu/teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    //1.查询所有
    @GetMapping("findAll")
    public List<Teacher> findAllTeacher(){
        List<Teacher> list = teacherService.list(null);
        return list;
    }

    //2.逻辑删除
    @DeleteMapping("{id}")
    public boolean removeTeacher(@PathVariable String id) {
        boolean flag = teacherService.removeById(id);
        return flag;
    }

}

