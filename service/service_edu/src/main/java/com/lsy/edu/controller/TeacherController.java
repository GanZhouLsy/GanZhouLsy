package com.lsy.edu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lsy.commonutils.R;
import com.lsy.edu.entity.Teacher;
import com.lsy.edu.entity.vo.TeacherQuery;
import com.lsy.edu.service.TeacherService;
import com.lsy.servicebase.exceptionhandler.GuliException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author lsy
 * @since 2023-08-25
 */
@Api(description = "讲师管理")
@RestController
@RequestMapping("/edu/teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    //1.查询所有
    @ApiOperation(value = "查询所有讲师")
    @GetMapping("findAll")
    public R findAllTeacher(){
        List<Teacher> list = teacherService.list(null);
        return R.ok().data("info", list);
    }

    //2.逻辑删除
    @ApiOperation(value = "逻辑删除讲师")
    @DeleteMapping("{id}")
    public R removeTeacher(@PathVariable String id) {
        boolean flag = teacherService.removeById(id);
        return flag ? R.ok() : R.error();
    }

    //3.分页查询
    @ApiOperation(value = "分页查询讲师")
    @GetMapping("page/{current}/{size}")
    public R pageListTeacher(@PathVariable long current,
                             @PathVariable long size) {

        Page<Teacher> pageTeacher = new Page<>(current,size);
        teacherService.page(pageTeacher,null);

        long total = pageTeacher.getTotal();
        List<Teacher> records = pageTeacher.getRecords();
        Map map = new HashMap();
        map.put("total", total);
        map.put("info", records);
        return R.ok().data(map);
    }
    //4.条件分页查询.
    @ApiOperation(value = "分页条件查询讲师")
    @PostMapping("pageCondition/{current}/{size}")
    public R pageCondition(@PathVariable long current,
                           @PathVariable long size,
                           @RequestBody(required = false) TeacherQuery teacherQuery){
        Page<Teacher> pageTeacher = new Page<>(current,size);
        //构建条件
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();

        try {//自定义异常测试
            int a = 10/0;
        }catch (Exception e) {
            throw new GuliException(2001,"自定义异常");
        }

        if (!StringUtils.isEmpty(name)){
            queryWrapper.like("name", name);
        }
        if (!StringUtils.isEmpty(level)){
            queryWrapper.like("level", level);
        }
        if (!StringUtils.isEmpty(begin)){
            queryWrapper.ge("gmt_create", begin);
        }
        if (!StringUtils.isEmpty(end)){
            queryWrapper.le("gmt_create", end);
        }

        teacherService.page(pageTeacher,queryWrapper);
        long total = pageTeacher.getTotal();
        List<Teacher> records = pageTeacher.getRecords();
        Map map = new HashMap();
        map.put("total", total);
        map.put("info", records);
        return R.ok().data(map);
    }

    //添加
    @ApiOperation(value = "添加讲师")
    @PostMapping("add")
    public R addTeacher(@RequestBody Teacher teacher){
        boolean save = teacherService.save(teacher);
        return save ? R.ok() : R.error();
    }

    //根据id查询
    @ApiOperation(value = "根据id查询讲师")
    @GetMapping("findById/{id}")
    public R findById(@PathVariable String id) {
        Teacher teacher = teacherService.getById(id);
        return R.ok().data("info", teacher);
    }

    //修改讲师信息
    @ApiOperation(value = "根据id修改讲师")
    @PostMapping("update")
    public R update(@RequestBody Teacher teacher){
        boolean flag = teacherService.updateById(teacher);
        return flag ? R.ok() : R.error();
    }
}

