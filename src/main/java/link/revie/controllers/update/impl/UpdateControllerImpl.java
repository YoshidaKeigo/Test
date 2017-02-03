package link.revie.controllers.update.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.validation.Valid;

import link.revie.controllers.update.UpdateController;
import link.revie.model.CategoryType;
import link.revie.model.MovieType;
import link.revie.model.entity.Article;
import link.revie.service.article.ArticleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;

@Component
public class UpdateControllerImpl implements UpdateController {

	@Autowired
	ArticleService articleService;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
	@Override
	public String form(@PathVariable("id") int id, Model model) {
		Article article = articleService.findById(id);
		
		model.addAttribute("mainContentPath", "update-article :: update-form");
		model.addAttribute("categories", CategoryType.itemValues());
		model.addAttribute("movieTypes", MovieType.values());
		model.addAttribute("article", article);	
		return "main";
	}

	@Override
	public String form(Article article, BindingResult bindingResult, Model model) {
		model.addAttribute("mainContentPath", "update-article :: update-form");
		model.addAttribute("categories", CategoryType.itemValues());
		model.addAttribute("movieTypes", MovieType.values());
		complementArticleRelation(article);
		model.addAttribute("article", article);	
		return "main";
	}

	@Override
	public String confirm(@Valid Article article, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("mainContentPath", "update-article :: update-form");
			model.addAttribute("movieTypes", MovieType.values());
		} else {
			model.addAttribute("mainContentPath", "update-article :: update-confirm");
		}
		model.addAttribute("categories", CategoryType.itemValues());
		complementArticleRelation(article);
		model.addAttribute("article", article);	
		return "main";
	}

	@Override
	public String register(Article article, Model model) {
		complementArticleRelation(article);
		articleService.save(article);
		
		model.addAttribute("mainContentPath", "update-article :: update-registered");
		model.addAttribute("categories", CategoryType.itemValues());
		return "main";
	}

	private void complementArticleRelation(Article article) {
		List<Article> complementedList = article.getArticles().stream()
			.map(a -> {
				if (Objects.nonNull(a.getId())) {
					return articleService.findById(a.getId());
				} else {
					return a;
				}
			}).collect(Collectors.toList());
		article.getArticles().clear();
		article.getArticles().addAll(complementedList);
	}
}
