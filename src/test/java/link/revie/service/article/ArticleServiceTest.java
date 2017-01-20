package link.revie.service.article;

import static org.junit.Assert.*;
import link.revie.model.CategoryType;
import link.revie.model.MovieType;
import link.revie.model.entity.Article;

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
	private ArticleService testee;

	@Test
	public void test() {
		Article article = new Article();
		article.setTitle("test");
		article.setYear(2017);
		article.setMonth(1);
		article.setMovieType(MovieType.DOMESTIC);
		article.setCategories(Lists.newArrayList(CategoryType.ACTION));
		
		Article result = testee.save(article);
		
		assertEquals(Integer.valueOf(1), result.getId());
	}

}
