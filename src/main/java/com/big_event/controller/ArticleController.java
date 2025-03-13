package com.big_event.controller;

import com.big_event.pojo.Article;
import com.big_event.pojo.PageBean;
import com.big_event.pojo.Result;
import com.big_event.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    ArticleService articleService;

    @PostMapping
    public Result add(@RequestBody @Validated Article article){
//        新增文章(发布文章)
        articleService.add(article);
        return Result.success();
    }

    @GetMapping
    public Result<PageBean<Article>> list(
            Integer pageNum,
            Integer pageSize,
            @RequestParam(required = false) Integer categoryId,
            @RequestParam(required = false) String state
    ){
//        根据条件查询文章,带分页
        PageBean<Article> pageBean = articleService.list(pageNum,pageSize,categoryId,state);
        return Result.success(pageBean);
    }

    @GetMapping("/detail")
    public Result<Article> detail(@Validated Integer id){
//        根据ID获取文章详细信息
        Article article = articleService.detail(id);
        return Result.success(article);
    }

    @PutMapping
    public Result<Article> update(@RequestBody Article article){
//        更新文章信息
        articleService.update(article);
        return Result.success();
    }

    @DeleteMapping
    public Result delete(Integer id){
        articleService.delete(id);
        return Result.success();
    }
}
