package com.big_event.service.impl;

import com.big_event.mapper.CategoryMapper;
import com.big_event.pojo.Category;
import com.big_event.service.CategoryService;
import com.big_event.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public void add(Category category) {
        category.setCreateTime(LocalDateTime.now());
        category.setUpdateTime(LocalDateTime.now());

        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        category.setCreateUser(userId);

        categoryMapper.add(category);
    }

    @Override
    public List<Category> list() {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        return categoryMapper.list(userId);
    }

    @Override
    public Category findById(Integer id) {
        return categoryMapper.detail(id);
    }

    @Override
    public void update(Category category) {
        category.setUpdateTime(LocalDateTime.now());
        categoryMapper.update(category);
    }

    @Override
    public void delete(Integer id) {
        categoryMapper.delete(id);
    }


}
