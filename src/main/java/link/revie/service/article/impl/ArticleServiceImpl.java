package link.revie.service.article.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import link.revie.model.entity.Article;
import link.revie.model.entity.ArticleArticle;
import link.revie.model.repository.ArticleRepository;
import link.revie.service.article.ArticleService;
import link.revie.service.articlearticle.ArticleArticleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.google.common.collect.Lists;

@Component
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	private ArticleRepository articleRepository;
	@Autowired
	private ArticleArticleService articleArticleService;

	@Override
	public List<Article> findAll() {
		return Optional.ofNullable(articleRepository.findAll())
				.orElse(Lists.newArrayList())
				.stream()
				.map(this::complement)
				.collect(Collectors.toList());
	}

	@Override
	public Article findById(Integer id) {
		return complement(articleRepository.findOne(id));
	}

	@Override
	public Article save(Article article) {
		if (!CollectionUtils.isEmpty(article.getArticles())) {
			List<Article> articles = articleRepository.save(article.getArticles());
			articles.stream().forEach(to -> articleArticleService.saveIfNothing(article, to));
		}
		return complement(articleRepository.save(article));
	}

	@Override
	public void delete(Article article) {
		articleRepository.delete(article);
		articleArticleService.deleteByFromId(article.getId());
	}

	private Article complement(Article article) {
		if (Objects.isNull(article)) {
			return null;
		}
		
		List<Integer> relatedId = Optional.ofNullable(articleArticleService.findByFromId(article.getId()))
				.orElse(Lists.newArrayList())
				.stream()
				.map(ArticleArticle::getToId)
				.collect(Collectors.toList());
		List<Article> articles = Optional.ofNullable(articleRepository.findAll(relatedId))
				.orElse(Lists.newArrayList());
		article.getArticles().addAll(articles);
		return article;
	}
}
