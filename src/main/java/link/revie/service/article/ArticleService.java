package link.revie.service.article;

import java.util.List;

import link.revie.model.entity.Article;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor=Exception.class)
public interface ArticleService {

	List<Article> findAll();
	
	Article findById(Integer id);
	
	List<Article> findByTitle(String title);

	List<Article> findByString(String string);
	
	List<Article> findByCategory(String code);
	
	List<Article> findByMovieType(String code);

	Article save(Article article);

	void delete(Article article);
}
