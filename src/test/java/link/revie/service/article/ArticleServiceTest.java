package link.revie.service.article;

import static org.junit.Assert.*;

import java.util.List;

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
		
		assertEquals("test", result.getTitle());
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
		
		assertEquals("test", result.getTitle());
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
		
		assertEquals(MovieType.FOREIGN, result.getArticles().iterator().next().getMovieType());
	}

	@Test
	public void test01_findByTitle() {
		Article article1 = new Article();
		article1.setTitle("ABCDEF");
		article1.setYear(2017);
		article1.setMonth(1);
		article1.setMovieType(MovieType.DOMESTIC);
		article1.setCategories(Lists.newArrayList(CategoryType.ACTION));
		article1.setPoint(80);
		
		Article article2 = new Article();
		article2.setTitle("GHIJKL");
		article2.setYear(2017);
		article2.setMonth(1);
		article2.setMovieType(MovieType.DOMESTIC);
		article2.setCategories(Lists.newArrayList(CategoryType.ACTION));
		article2.setPoint(80);
		
		testee.save(article1);
		testee.save(article2);
		
		List<Article> list = testee.findByTitle("CD");
		Article result = list.iterator().next();
		
		assertEquals("ABCDEF", result.getTitle());
	}

	@Test
	public void test01_findByString() {
		Article article1 = new Article();
		article1.setTitle("ABCDEF");
		article1.setYear(2017);
		article1.setMonth(1);
		article1.setMovieType(MovieType.DOMESTIC);
		article1.setCategories(Lists.newArrayList(CategoryType.ACTION));
		article1.setText("one");
		article1.setPoint(80);
		
		Article article2 = new Article();
		article2.setTitle("GHIJKL");
		article2.setYear(2017);
		article2.setMonth(1);
		article2.setMovieType(MovieType.DOMESTIC);
		article2.setCategories(Lists.newArrayList(CategoryType.ACTION));
		article2.setText("two");
		article2.setPoint(80);
		
		testee.save(article1);
		testee.save(article2);
		
		List<Article> list = testee.findByString("CD");
		Article result = list.iterator().next();
		
		assertEquals("ABCDEF", result.getTitle());
	}

	@Test
	public void test02_findByString() {
		Article article1 = new Article();
		article1.setTitle("ABCDEF");
		article1.setYear(2017);
		article1.setMonth(1);
		article1.setMovieType(MovieType.DOMESTIC);
		article1.setCategories(Lists.newArrayList(CategoryType.ACTION));
		article1.setText("one");
		article1.setPoint(80);
		
		Article article2 = new Article();
		article2.setTitle("GHIJKL");
		article2.setYear(2017);
		article2.setMonth(1);
		article2.setMovieType(MovieType.DOMESTIC);
		article2.setCategories(Lists.newArrayList(CategoryType.ACTION));
		article2.setText("two");
		article2.setPoint(80);
		
		testee.save(article1);
		testee.save(article2);
		
		List<Article> list = testee.findByString("wo");
		Article result = list.iterator().next();
		
		assertEquals("GHIJKL", result.getTitle());
	}

	@Test
	public void test01_findByCategory() {
		Article article1 = new Article();
		article1.setTitle("ABCDEF");
		article1.setYear(2017);
		article1.setMonth(1);
		article1.setMovieType(MovieType.DOMESTIC);
		article1.setCategories(Lists.newArrayList(CategoryType.NONFICTION));
		article1.setText("one");
		article1.setPoint(80);
		
		Article article2 = new Article();
		article2.setTitle("GHIJKL");
		article2.setYear(2017);
		article2.setMonth(1);
		article2.setMovieType(MovieType.DOMESTIC);
		article2.setCategories(Lists.newArrayList(CategoryType.ANIME));
		article2.setText("two");
		article2.setPoint(80);
		
		testee.save(article1);
		testee.save(article2);
		
		List<Article> list = testee.findByCategory(CategoryType.NONFICTION.getCode());
		Article result = list.iterator().next();
		
		assertEquals("ABCDEF", result.getTitle());
	}

	@Test
	public void test01_findByMovieType() {
		Article article1 = new Article();
		article1.setTitle("ABCDEF");
		article1.setYear(2017);
		article1.setMonth(1);
		article1.setMovieType(MovieType.DOMESTIC);
		article1.setCategories(Lists.newArrayList(CategoryType.NONFICTION));
		article1.setText("one");
		article1.setPoint(80);
		
		Article article2 = new Article();
		article2.setTitle("GHIJKL");
		article2.setYear(2017);
		article2.setMonth(1);
		article2.setMovieType(MovieType.FOREIGN);
		article2.setCategories(Lists.newArrayList(CategoryType.ANIME));
		article2.setText("two");
		article2.setPoint(80);
		
		testee.save(article1);
		testee.save(article2);
		
		List<Article> list = testee.findByMovieType(MovieType.FOREIGN.getCode());
		Article result = list.iterator().next();
		
		assertEquals("GHIJKL", result.getTitle());
	}

}
