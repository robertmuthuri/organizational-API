package models.DAO;

import models.Department;
import models.DepartmentNews;
import org.junit.After;
import org.junit.Before;

import org.junit.Test;
import org.sql2o.*;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class DepartmentNewsDaoTest {

    private DepartmentNewsDAO departmentNewsDao;
    private DepartmentDAO departmentDao;
    private Connection conn;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        departmentNewsDao = new DepartmentNewsDAO(sql2o);
        departmentDao = new DepartmentDAO(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }
    // helper methods
    public DepartmentNews setupNewDepartmentNews() {
        return new DepartmentNews("To wait or not to wait?!","Pelosi's big nightmare recently, do we impeach trump or not, or now or when?");
    }
    public Department setupAltDepartment (){
        Department department = new Department("Sports", "Sports news and events", 7);
        departmentDao.add(department);
        return department;
    }

    public Department setupDepartment (){
        Department department = new Department("Business", "Business news and events", 12);
        departmentDao.add(department);
        return department;
    }
    @Test
    public void addingDepartmentNewsSetsId() throws Exception {
        DepartmentNews testDepartmentNews = setupNewDepartmentNews();
        int originalDepartmentNewsId = testDepartmentNews.getId();
        departmentNewsDao.add(testDepartmentNews);
        assertNotEquals(originalDepartmentNewsId, testDepartmentNews.getId());
    }
    @Test
    public void addedDepartmentNewsAreReturnedFromGetAll() throws Exception {
        DepartmentNews testDepartmentNews = setupNewDepartmentNews();
        departmentNewsDao.add(testDepartmentNews);
        assertEquals(1, departmentNewsDao.getAll().size());
    }
    @Test
    public void noRecordsOfDepartmentNewsReturnsEmptyList() throws Exception {
        assertEquals(0, departmentNewsDao.getAll().size());
    }
    @Test
    public void deleteByIdDeletesCorrectDepartmentNews() throws Exception {
        DepartmentNews testDepartmentNews = setupNewDepartmentNews();
        departmentNewsDao.add(testDepartmentNews);
        departmentNewsDao.deleteById(testDepartmentNews.getId());
        assertEquals(0, departmentNewsDao.getAll().size());
    }
    @Test
    public void clearAll() throws Exception {
        DepartmentNews testDepartmentNews = setupNewDepartmentNews();
        DepartmentNews otherDepartmentNews = setupNewDepartmentNews();
        departmentNewsDao.clearAll();
        assertEquals(0, departmentNewsDao.getAll().size());
    }
    @Test
    public void addDepartmentNewsToDepartmentAddsTypeCorrectly() throws Exception {

        Department testDepartment = setupDepartment();
        Department altDepartment = setupAltDepartment();

        departmentDao.add(testDepartment);
        departmentDao.add(altDepartment);

        DepartmentNews testDepartmentNews = setupNewDepartmentNews();

        departmentNewsDao.add(testDepartmentNews);

        departmentNewsDao.addDepartmentNewsToDepartment(testDepartmentNews, testDepartment);
        departmentNewsDao.addDepartmentNewsToDepartment(testDepartmentNews, altDepartment);

        Department[] departments = {testDepartment,altDepartment};

        assertEquals(Arrays.asList(departments), departmentNewsDao.getAllDepartmentsForADepartmentNewsArticle(testDepartmentNews.getId()));
    }
    @Test
    public void deleteAlsoUpdatesJoinTable() throws Exception {
        DepartmentNews testDepartmentNews = setupNewDepartmentNews();
        departmentNewsDao.add(testDepartmentNews);

        DepartmentNews altTestDepartmentNews  = new DepartmentNews("What's cooking on Walstreet?","Markets grumble as Facebook announces new cryptocurrency");
        departmentNewsDao.add(altTestDepartmentNews);

        Department testDepartment = setupDepartment();
        departmentDao.add(testDepartment);

        Department altDepartment = setupAltDepartment();
        departmentDao.add(altDepartment);

        departmentNewsDao.addDepartmentNewsToDepartment(testDepartmentNews,testDepartment);
        departmentNewsDao.addDepartmentNewsToDepartment(altTestDepartmentNews, altDepartment);

        departmentNewsDao.deleteById(testDepartmentNews.getId());
        assertEquals(0, departmentNewsDao.getAllDepartmentsForADepartmentNewsArticle(testDepartmentNews.getId()).size());
    }
}