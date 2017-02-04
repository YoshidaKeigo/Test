package link.revie.controllers.movietype.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import com.google.common.collect.Lists;

import link.revie.controllers.movietype.MovieTypeController;
import link.revie.model.CategoryType;
import link.revie.model.MovieType;
import link.revie.model.entity.Article;
import link.revie.service.article.ArticleService;

@Component
public class MovieTypeControllerImpl implements MovieTypeController {

	@Autowired
	ArticleService articleService;
	
	@Override
	public String get(@PathVariable("type") String type, Model model) {
		MovieType movieType = MovieType.of(type);
		List<Article> articles = Lists.newArrayList(articleService.findByMovieType(type));
		
		model.addAttribute("mainContentPath", "main-content :: main-content");
		model.addAttribute("headerMessage", movieType.getName() + "の検索結果");
		model.addAttribute("categories", CategoryType.values());
		model.addAttribute("articles", articles);
		return "main";
	}

}
