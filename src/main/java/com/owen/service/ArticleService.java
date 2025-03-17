package com.owen.service;

import com.owen.pojo.Article;
import com.owen.pojo.PageBean;

import java.util.List;

public interface ArticleService {
	List<Article> findAll();

	void add(Article article);

	void update(Article article);

	void delete(Integer id);

	Article getById(Integer id);

	PageBean<Article> list(Integer pageNum, Integer pageSize, String categoryId, String status);
}
