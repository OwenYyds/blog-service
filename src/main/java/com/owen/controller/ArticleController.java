package com.owen.controller;

import com.owen.pojo.Article;
import com.owen.pojo.PageBean;
import com.owen.pojo.ResponseMessage;
import com.owen.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleController {

	@Autowired
	private ArticleService articleService;

	@GetMapping("/{id}")
	public ResponseMessage<Article> getById(@PathVariable Integer id) {
		return ResponseMessage.success(articleService.getById(id));
	}

	@GetMapping("/list")
	public ResponseMessage<PageBean<Article>> list(
			Integer pageNum,
			Integer pageSize,
		    @RequestParam(required = false) String categoryId,
			@RequestParam(required = false) String status
	) {
		PageBean<Article> pageList = articleService.list(pageNum, pageSize, categoryId, status);

		return ResponseMessage.success(pageList   );
	}

	@GetMapping
	public ResponseMessage<List<Article>> findAll() {
		return ResponseMessage.success(articleService.findAll());
	}

	@PostMapping("/add")
	public ResponseMessage<Article> add(@RequestBody @Validated Article article) {
		articleService.add(article);
		return ResponseMessage.success(article);
	}

	@PutMapping("/update")
	public ResponseMessage<Article> update(@RequestBody @Validated Article article) {
		articleService.update(article);
		return ResponseMessage.success(article);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseMessage<Article> delete(@PathVariable Integer id) {
		articleService.delete(id);
		return ResponseMessage.success();
	}


}
