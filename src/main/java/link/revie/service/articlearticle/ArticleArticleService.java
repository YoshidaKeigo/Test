package link.revie.service.articlearticle;

import java.util.List;

import link.revie.model.entity.Article;
import link.revie.model.entity.ArticleArticle;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor=Exception.class)
public interface ArticleArticleService {

	ArticleArticle saveIfNothing(Article from, Article to);

	ArticleArticle findById(Integer id);

	List<ArticleArticle> findByFromId(Integer fromId);

	ArticleArticle save(ArticleArticle article);

	void delete(ArticleArticle article);
	
	void deleteByFromId(Integer fromId);
}
