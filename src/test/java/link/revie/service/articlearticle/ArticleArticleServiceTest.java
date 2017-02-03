package link.revie.service.articlearticle;

import static org.junit.Assert.*;
import link.revie.model.entity.Article;
import link.revie.model.entity.ArticleArticle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ArticleArticleServiceTest {

	@Autowired
	private ArticleArticleService testee;

	@Test
	public void test01() {
		Article article1 = new Article();
		article1.setId(1);
		Article article2 = new Article();
		article2.setId(2);
		
		ArticleArticle result = testee.saveIfNothing(article1, article2);
		
		assertEquals(Integer.valueOf(1), result.getFromId());
		assertEquals(Integer.valueOf(2), result.getToId());
	}

}
