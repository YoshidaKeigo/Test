package link.revie.controllers.search.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import com.google.common.collect.Lists;

import link.revie.controllers.search.SearchController;
import link.revie.model.CategoryType;
import link.revie.model.entity.Article;
import link.revie.service.article.ArticleService;

@Component
public class SearchControllerImpl implements SearchController {

	@Autowired
	ArticleService articleService;

	@Override
	public String title(@PathVariable("title") String title, Model model) {
		List<Article> articles = Lists.newArrayList(articleService.findByTitle(title));
		
		model.addAttribute("mainContentPath", "main-content :: main-content");
		model.addAttribute("headerMessage", "「" + title + "」の検索結果");
		model.addAttribute("categories", CategoryType.values());
		model.addAttribute("articles", articles);
		return "main";
	}

	@Override
	public String string(@PathVariable("string") String string, Model model) {
		List<Article> articles = Lists.newArrayList(articleService.findByString(string));
		
		model.addAttribute("mainContentPath", "main-content :: main-content");
		model.addAttribute("headerMessage", "「" + string + "」の検索結果");
		model.addAttribute("categories", CategoryType.values());
		model.addAttribute("articles", articles);
		return "main";
	}

}
