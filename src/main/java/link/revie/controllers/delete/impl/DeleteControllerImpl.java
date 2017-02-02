package link.revie.controllers.delete.impl;

import link.revie.controllers.delete.DeleteController;
import link.revie.model.CategoryType;
import link.revie.model.MovieType;
import link.revie.service.article.ArticleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

@Component
public class DeleteControllerImpl implements DeleteController {
	
	@Autowired
	ArticleService articleService;
	
	@Override
	public String execute(@PathVariable("id") int id, Model model) {
		articleService.delete(articleService.findById(id));
		
		model.addAttribute("mainContentPath", "delete-article :: delete-executed");
		model.addAttribute("categories", CategoryType.values());
		model.addAttribute("movieTypes", MovieType.values());
		return "main";
	}

}
