package link.revie.service.article;

import static org.junit.Assert.*;
import link.revie.model.CategoryType;
import link.revie.model.MovieType;
import link.revie.model.entity.Article;
import link.revie.model.repository.ArticleRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.google.common.collect.Lists;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ArticleServiceTest {
	
	@Autowired
	private ArticleRepository articleRepository;
	
	@Autowired
	private ArticleService testee;

	@Test
	public void test01() {
		Article article = new Article();
		article.setTitle("test");
		article.setYear(2017);
		article.setMonth(1);
		article.setMovieType(MovieType.DOMESTIC);
		article.setCategories(Lists.newArrayList(CategoryType.ACTION));
		article.setPoint(80);
		
		Article result = testee.save(article);
		
		assertEquals(Integer.valueOf(100), result.getId());
	}

	@Test
	public void test02() {
		Article article1 = new Article();
		article1.setTitle("test");
		article1.setYear(2017);
		article1.setMonth(1);
		article1.setMovieType(MovieType.DOMESTIC);
		article1.setCategories(Lists.newArrayList(CategoryType.ACTION));
		article1.setPoint(80);
		
		Article article2 = new Article();
		article2.setTitle("test2");
		article2.setYear(2017);
		article2.setMonth(1);
		article2.setMovieType(MovieType.DOMESTIC);
		article2.setCategories(Lists.newArrayList(CategoryType.ACTION));
		article2.setPoint(80);
		
		article1.getArticles().add(article2);
		Article result = testee.save(article1);
		
		assertEquals(Integer.valueOf(102), result.getId());
	}

	@Test
	public void test03() {
		Article article1 = new Article();
		article1.setTitle("test");
		article1.setYear(2017);
		article1.setMonth(1);
		article1.setMovieType(MovieType.DOMESTIC);
		article1.setCategories(Lists.newArrayList(CategoryType.ACTION));
		article1.setPoint(80);
		
		Article article2 = new Article();
		article2.setTitle("test2");
		article2.setYear(2017);
		article2.setMonth(1);
		article2.setMovieType(MovieType.DOMESTIC);
		article2.setCategories(Lists.newArrayList(CategoryType.ACTION));
		article2.setPoint(80);
				
		article1.getArticles().add(article2);
		Article saved1 = testee.save(article1);
		
		Article saved2 = saved1.getArticles().iterator().next();
		saved2.setMovieType(MovieType.FOREIGN);
		
		Article result = testee.save(saved1);
		
		assertEquals(Integer.valueOf(104), result.getId());
		assertEquals(MovieType.FOREIGN, result.getArticles().iterator().next().getMovieType());
	}

}
