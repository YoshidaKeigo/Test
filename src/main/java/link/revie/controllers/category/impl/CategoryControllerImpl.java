package link.revie.controllers.category.impl;

import java.util.List;

import link.revie.controllers.category.CategoryController;
import link.revie.model.CategoryType;
import link.revie.model.entity.Article;
import link.revie.service.article.ArticleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import com.google.common.collect.Lists;

@Component
public class CategoryControllerImpl implements CategoryController {

	@Autowired
	ArticleService articleService;
	
	@Override
	public String get(@PathVariable("category") String category, Model model) {
		CategoryType categoryType = CategoryType.getFromPath(category).get();
		List<Article> articles = Lists.newArrayList(articleService
				.findByCategory(categoryType.getCode()));
		
		model.addAttribute("mainContentPath", "main-content :: main-content");
		model.addAttribute("headerMessage", categoryType.getName() + "の検索結果");
		model.addAttribute("categories", CategoryType.values());
		model.addAttribute("articles", articles);
		return "main";
	}

}
