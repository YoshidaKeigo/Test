package link.revie.controllers.add.impl;

import javax.validation.Valid;

import link.revie.controllers.add.AddController;
import link.revie.model.CategoryType;
import link.revie.model.MovieType;
import link.revie.model.entity.Article;
import link.revie.service.article.ArticleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

@Component
public class AddControllerImpl implements AddController {

	@Autowired
	ArticleService articleService;
	
	@Override
	public String form(Model model) {
		model.addAttribute("mainContentPath", "add-article :: add-form");
		model.addAttribute("categories", CategoryType.itemValues());
		model.addAttribute("movieTypes", MovieType.values());
		model.addAttribute("article", new Article());	
		return "main";
	}

	@Override
	public String form(Article article, BindingResult bindingResult, Model model) {
		model.addAttribute("mainContentPath", "add-article :: add-form");
		model.addAttribute("categories", CategoryType.itemValues());
		model.addAttribute("movieTypes", MovieType.values());
		model.addAttribute("article", article);	
		return "main";
	}

	@Override
	public String confirm(@Valid Article article, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("mainContentPath", "add-article :: add-form");
			model.addAttribute("movieTypes", MovieType.values());
		} else {
			model.addAttribute("mainContentPath", "add-article :: add-confirm");
		}
		model.addAttribute("categories", CategoryType.itemValues());
		model.addAttribute("article", article);	
		return "main";
	}

	@Override
	public String register(Article article, Model model) {
		articleService.save(article);
		
		model.addAttribute("mainContentPath", "add-article :: add-registered");
		model.addAttribute("categories", CategoryType.itemValues());
		return "main";
	}

}
