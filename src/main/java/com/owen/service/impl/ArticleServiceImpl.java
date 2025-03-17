package com.owen.service.impl;

import com.owen.mapper.ArticleMapper;
import com.owen.pojo.Article;
import com.owen.service.ArticleService;
import com.owen.utills.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	private ArticleMapper articleMapper;

	@Override
	public List<Article> findAll() {
		return articleMapper.findAll();
	}

	@Override
	public void add(Article article) {
		Map<String, Object> map = ThreadLocalUtil.get();
		Integer userId = (Integer) map.get("id");
		article.setUserId(userId);
		articleMapper.add(article);
	}

	@Override
	public void update(Article article) {
		articleMapper.update(article);
	}

	@Override
	public void delete(Integer id) {
		articleMapper.delete(id);
	}

	@Override
	public Article getById(Integer id) {
		return articleMapper.getById(id);
	}
}
