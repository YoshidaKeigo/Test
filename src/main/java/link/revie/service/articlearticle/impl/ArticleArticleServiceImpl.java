package link.revie.service.articlearticle.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import link.revie.model.entity.Article;
import link.revie.model.entity.ArticleArticle;
import link.revie.model.repository.ArticleArticleRepository;
import link.revie.service.articlearticle.ArticleArticleService;

@Component
public class ArticleArticleServiceImpl implements ArticleArticleService {

	@Autowired
	private ArticleArticleRepository articleArticleRepository;
	
	@Override
	public ArticleArticle saveIfNothing(Article from, Article to) {
		ArticleArticle articleArticle = Optional.ofNullable(articleArticleRepository
				.findByFromIdAndToId(from.getId(), to.getId()))
				.orElseGet(() -> {
					ArticleArticle ret = new ArticleArticle();
					ret.setFromId(from.getId());
					ret.setToId(to.getId());
					return save(ret);
				});
		return articleArticle;
	}

	@Override
	public ArticleArticle findById(Integer id) {
		return articleArticleRepository.findOne(id);
	}

	@Override
	public List<ArticleArticle> findByFromId(Integer fromId) {
		return articleArticleRepository.findByFromId(fromId);
	}

	@Override
	public ArticleArticle save(ArticleArticle article) {
		return articleArticleRepository.save(article);
	}

	@Override
	public void delete(ArticleArticle article) {
		articleArticleRepository.delete(article);
	}

	@Override
	public void deleteByFromId(Integer fromId) {
		List<ArticleArticle> relations = findByFromId(fromId);
		if (!CollectionUtils.isEmpty(relations)) {
			articleArticleRepository.delete(relations);
		}
	}

}
