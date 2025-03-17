package com.owen.mapper;


import com.owen.pojo.Article;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ArticleMapper {


	@Select("select * from article where id=#{id}")
	Article getById(Integer id);

	@Select("select * from article")
	List<Article> findAll();

	@Insert("insert into article(user_id, title, content, summary, cover_image, category_id) " +
			"values(#{userId}, #{title}, #{content}, #{summary}, #{coverImage}, #{categoryId})")
	void add(Article article);

	@Update("update article set title=#{title}, content=#{content} where id=#{id}")
	void update(Article article);

	@Update("update article set deleted=1 where id=#{id}")
	void delete(Integer id);

	List<Article> list(String categoryId, String status);
}
