package com.big_event.mapper;

import com.big_event.pojo.Category;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CategoryMapper {
    //新增文章分类
    @Insert(" insert into category (category_name, category_alias, create_user, create_time, update_time) " +
            " values  (#{categoryName},#{categoryAlias},#{createUser},#{createTime},#{updateTime})  ")
    void add(Category category);


    //获取当前已登录用户创建的所有文章分类
    @Select("select * from category where create_user = #{userId}")
    List<Category> list(Integer userId);

//    根据ID获取文章分类详情
    @Select("select  * from category where id = #{id}")
    Category detail(Integer id);

//    更新文章分类
    @Update("update category set category_name = #{categoryName} , category_alias = #{categoryAlias} , update_time = #{updateTime} where id = #{id}")
    void update(Category category);

    //根据ID删除文章分类
    @Delete("delete from category where id = #{id}")
    void delete(Integer id);
}
