package com.big_event.controller;

import com.big_event.pojo.Category;
import com.big_event.pojo.Result;
import com.big_event.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public Result add(@RequestBody @Validated Category category){
        //新增文章分类
        categoryService.add(category);
        return Result.success();
    }

    @GetMapping
    public Result<List<Category>> list(){
        //获取当前已登录用户创建的所有文章分类
        List<Category> category = categoryService.list();
        return Result.success(category);
    }

    @GetMapping("/detail")
    public Result<Category> detail(Integer id){
//        根据ID获取文章分类详情
        Category category = categoryService.findById(id);
        return Result.success(category);
    }

    @PutMapping
    public Result<Category> update(@RequestBody @Validated Category category){
//        更新文章分类
        categoryService.update(category);
        return Result.success();
    }

    @DeleteMapping
    public Result<Category> delete(Integer id){
        categoryService.delete(id);
        return Result.success();
    }
}
