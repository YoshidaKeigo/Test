package link.revie.controllers.main.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.google.common.collect.Lists;

import link.revie.controllers.main.MainController;
import link.revie.model.CategoryType;
import link.revie.model.entity.Article;
import link.revie.service.article.ArticleService;

@Component
public class MainControllerImpl implements MainController {

	@Autowired
	ArticleService articleService;
	
	@Override
	public String main(Model model) {
		List<CategoryType> categories = Lists.newArrayList(CategoryType.values());
		List<Article> articleList = articleService.findAll();
		
		model.addAttribute("categories", categories);	
		return "main";
	}
	
}
