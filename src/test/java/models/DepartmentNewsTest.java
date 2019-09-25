package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DepartmentNewsTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    // helper method
    public DepartmentNews setupDepartmentNewsArticle(){
        return new DepartmentNews("Supreme Court: Suspending Parliament was unlawful, judges rule!","https://www.bbc.com/news/uk-politics-49810261");
    }
    @Test
    public void setId() {
        DepartmentNews testDepartmentNews = setupDepartmentNewsArticle();
        testDepartmentNews.setId(5);
        assertEquals(5, testDepartmentNews.getId());
    }
    @Test
    public void setHeading() {
        DepartmentNews testDepartmentNews = setupDepartmentNewsArticle();
        testDepartmentNews.setHeading("Corporate DepartmentNews");
        assertNotEquals("Business Daily", testDepartmentNews.getHeading());
    }
    @Test
    public void getHeading() {
        DepartmentNews testDepartmentNews = setupDepartmentNewsArticle();
        assertEquals("Supreme Court: Suspending Parliament was unlawful, judges rule!", testDepartmentNews.getHeading());
    }
    @Test
    public void setDepartmentNews_link() {
        DepartmentNews testDepartmentNews = setupDepartmentNewsArticle();
        testDepartmentNews.setNews_link("https://www.pulselive.co.ke/bi/politics/the-uk-supreme-court-rules-that-boris-johnsons-suspension-of-parliament-was-illegal/3shwmmg");
        assertNotEquals("https://www.bbc.com/news/uk-politics-49810261", testDepartmentNews.getNews_link());
    }
    @Test
    public void getDepartmentNews_link() {
        DepartmentNews testDepartmentNews = setupDepartmentNewsArticle();
        assertEquals("https://www.bbc.com/news/uk-politics-49810261", testDepartmentNews.getNews_link());
    }

    @Test
    public void getDepartmentNews_type() {
        DepartmentNews testDepartmentNews = setupDepartmentNewsArticle();
        assertEquals("department_news", testDepartmentNews.getNews_type());
    }
}