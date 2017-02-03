package link.revie.model.repository;

import java.util.List;

import link.revie.model.entity.ArticleArticle;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleArticleRepository extends JpaRepository<ArticleArticle, Integer> {
	
	@Query(value = "select * from ARTICLE_ARTICLE where FROM_ID = :fromId and TO_ID = :toId limit 1", nativeQuery = true)
	ArticleArticle findByFromIdAndToId(@Param("fromId") Integer fromId, @Param("toId") Integer toId);
	
	@Query(value = "select * from ARTICLE_ARTICLE where FROM_ID = :fromId", nativeQuery = true)
	List<ArticleArticle> findByFromId(@Param("fromId") Integer fromId);
}
