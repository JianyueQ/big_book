package com.big_event.service.impl;

import com.big_event.mapper.ArticleMapper;
import com.big_event.pojo.Article;
import com.big_event.pojo.PageBean;
import com.big_event.service.ArticleService;
import com.big_event.utils.ThreadLocalUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ArticleMapper articleMapper;

    @Override
    public void add(Article article) {
        article.setCreateTime(LocalDateTime.now());
        article.setUpdateTime(LocalDateTime.now());
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer UserId = (Integer) map.get("id");
        article.setCreateUser(UserId);
        articleMapper.add(article);
    }

    @Override
    public PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state) {
        //创建PageBean对象
        PageBean<Article> pageBean = new PageBean<>();
        //开启分页查询
        PageHelper.startPage(pageNum,pageSize);
        //调用mapper
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer UserId = (Integer) map.get("id");
        List<Article> articles = articleMapper.list(categoryId,state,UserId);
        //Page中提供了方法，pageHelper分页查询后，得到的总记录条数和当前数据
        Page<Article> p = (Page<Article>) articles;
        //把数据填充到PageBean对象
        pageBean.setTotal(p.getTotal());
        pageBean.setItems(p.getResult());
        return pageBean;
    }

    @Override
    public Article detail(Integer id) {

        return articleMapper.detail(id);
    }

    @Override
    public void update(Article article) {
        article.setUpdateTime(LocalDateTime.now());
        articleMapper.update(article);
    }

    @Override
    public void delete(Integer id) {
        articleMapper.delete(id);
    }


}
