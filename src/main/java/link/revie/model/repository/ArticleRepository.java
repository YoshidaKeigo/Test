package link.revie.model.repository;

import java.util.List;

import link.revie.model.MovieType;
import link.revie.model.entity.Article;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {
	
	@Query(value = "select * from ARTICLE where TITLE like %:title%", nativeQuery = true)
	List<Article> findByTitle(@Param("title") String title);

	@Query(value = "select * from ARTICLE where TITLE like %:string% or TEXT like %:string%", nativeQuery = true)
	List<Article> findByString(@Param("string") String string);

	@Query(value = "select * from ARTICLE, ARTICLE_CATEGORY where ARTICLE_CATEGORY.CATEGORY = :category and ARTICLE.ID = ARTICLE_CATEGORY.ARTICLE_ID", nativeQuery = true)
	List<Article> findByCategory(@Param("category") String category);
	
	List<Article> findByMovieType(MovieType movieType);
	
}
