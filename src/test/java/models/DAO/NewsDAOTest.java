package models.DAO;
import models.News;
import org.h2.util.New;
import org.junit.Test;
import org.sql2o.*;
import org.junit.After;
import org.junit.Before;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

public class NewsDAOTest {
    private NewsDAO newsDao;
    private Connection conn;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        newsDao = new NewsDAO(sql2o);
        conn = sql2o.open();
    }
    @After
    public void tearDown() throws Exception {
        conn.close();
    }
    // Helper method
    public News setupNewsArticle() {
        return new News("Do or Die-Trump is fixed!","The Democratic party has officialy moved to open impeachment proceedings against Donald Trump!");
    }
    @Test
    public void addingArticleSetsId() throws Exception {
        News testNews = setupNewsArticle();
        int originalNewsId = testNews.getId();
        newsDao.add(testNews);
        assertNotEquals(originalNewsId, testNews.getId());
    }
    @Test
    public void addedArticlesAreReturnedFromGetAll() throws Exception {
        News testNews = setupNewsArticle();
        newsDao.add(testNews);
        assertEquals(1, newsDao.getAll().size());
    }
    @Test
    public void noArticlesReturnsEmptyList() throws Exception {
        assertEquals(0, newsDao.getAll().size());
    }
    @Test
    public void deleteByIdDeletesCorrectArticle() throws Exception {
        News testNews = setupNewsArticle();
        newsDao.add(testNews);
        newsDao.deleteById(testNews.getId());
        assertEquals(0, newsDao.getAll().size());
    }
    @Test
    public void clearAll() throws Exception {
        News testNews = setupNewsArticle();
        News otherNews = setupNewsArticle();
        newsDao.clearAll();
        assertEquals(0, newsDao.getAll().size());
    }
}