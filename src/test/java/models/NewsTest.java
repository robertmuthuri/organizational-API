package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NewsTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    // helper method
    public News setupNewsArticle(){
        return new News("Supreme Court: Suspending Parliament was unlawful, judges rule!","https://www.bbc.com/news/uk-politics-49810261");
    }
    @Test
    public void setId() {
        News testNews = setupNewsArticle();
        testNews.setId(5);
        assertEquals(5, testNews.getId());
    }
    @Test
    public void setHeading() {
        News testNews = setupNewsArticle();
        testNews.setHeading("Corporate News");
        assertNotEquals("Business Daily", testNews.getHeading());
    }
    @Test
    public void getHeading() {
        News testNews = setupNewsArticle();
        assertEquals("Supreme Court: Suspending Parliament was unlawful, judges rule!", testNews.getHeading());
    }
    @Test
    public void setNews_link() {
        News testNews = setupNewsArticle();
        testNews.setNews_link("https://www.pulselive.co.ke/bi/politics/the-uk-supreme-court-rules-that-boris-johnsons-suspension-of-parliament-was-illegal/3shwmmg");
        assertNotEquals("https://www.bbc.com/news/uk-politics-49810261", testNews.getNews_link());
    }
    @Test
        public void getNews_link() {
            News testNews = setupNewsArticle();
            assertEquals("https://www.bbc.com/news/uk-politics-49810261", testNews.getNews_link());
    }

    @Test
    public void getNews_type() {
        News testNews = setupNewsArticle();
        assertEquals("general_news", testNews.getNews_type());
    }
}