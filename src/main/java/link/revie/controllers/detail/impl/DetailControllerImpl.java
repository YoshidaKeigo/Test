package link.revie.controllers.detail.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import link.revie.controllers.detail.DetailController;
import link.revie.model.CategoryType;
import link.revie.model.entity.Article;
import link.revie.service.article.ArticleService;

@Component
public class DetailControllerImpl implements DetailController {

	@Autowired
	ArticleService articleService;
	
	@Override
	public String detail(@PathVariable("id") int id, Model model) {
		Article article = articleService.findById(Integer.valueOf(id));
		
		model.addAttribute("mainContentPath", "detail-article :: detail-article");
		model.addAttribute("categories", CategoryType.values());
		model.addAttribute("article", article);
		return "main";
	}

}
