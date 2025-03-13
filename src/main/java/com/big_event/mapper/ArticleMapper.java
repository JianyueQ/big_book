package com.big_event.mapper;

import com.big_event.pojo.Article;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface ArticleMapper {
    //新增文章(发布文章)
    @Insert("insert into article (id, title, content, cover_img, state, category_id, create_user, create_time, update_time) "+
    "values (#{id},#{title},#{content},#{coverImg},#{state},#{categoryId},#{createUser},#{createTime},#{updateTime})")
    void add(Article article);
    //根据条件查询文章,带分页
    List<Article> list(Integer categoryId, String state, Integer userId);

    Article detail(Integer id);

    void update(Article article);

    void delete(Integer id);
}
