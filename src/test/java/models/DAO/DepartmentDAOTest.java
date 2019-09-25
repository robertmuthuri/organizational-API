package models.DAO;
import models.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class DepartmentDAOTest {
    private DepartmentNewsDAO departmentNewsDao;
    private DepartmentDAO departmentDao;
    private Connection conn;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        departmentDao = new DepartmentDAO(sql2o);
        departmentNewsDao = new DepartmentNewsDAO(sql2o);
        conn = sql2o.open();
    }
    @After
    public void tearDown() throws Exception {
        conn.close();
    }
    // helper methods
    public Department setupDepartment (){
        Department department = new Department("Business", "Business related news", 12);
        return department;
    }
    public Department setupAltDepartment (){
        Department department = new Department("Sports", "Sports news", 6);
        return department;
    }
    @Test
    public void addingDepartmentSetsId() throws Exception {
        Department testDepartment = setupDepartment();
        int originalDepartmentId = testDepartment.getId();
        departmentDao.add(testDepartment);
        assertNotEquals(originalDepartmentId, testDepartment.getId());
    }
    @Test
    public void addedDepartmentsAreReturnedFromGetAll() throws Exception {
        Department testDepartment = setupDepartment();
        departmentDao.add(testDepartment);
        assertEquals(1, departmentDao.getAll().size());
    }
    @Test
    public void noDepartmentsReturnsEmptyList() throws Exception {
        assertEquals(0, departmentDao.getAll().size());
    }
    @Test
    public void deleteByIdDeletesCorrectDepartment() throws Exception {
        Department department = setupDepartment();
        departmentDao.add(department);
        departmentDao.deleteById(department.getId());
        assertEquals(0, departmentDao.getAll().size());
    }
    @Test
    public void clearAll() throws Exception {
        Department testDepartment = setupDepartment();
        Department otherDepartment = setupAltDepartment();
        departmentDao.add(testDepartment);
        departmentDao.add(otherDepartment);
        departmentDao.clearAll();
        assertEquals(0, departmentDao.getAll().size());
    }
    @Test
    public void DepartmentReturnsDepartmentNewsCorrectly() throws Exception {
        DepartmentNews testDepartmentNews  = new DepartmentNews("Nyama mama","The new restaurant in town making heads roll");
        departmentNewsDao.add(testDepartmentNews);
        DepartmentNews otherDepartmentNews  = new DepartmentNews("5 things you don't know about Mugabe", "The 4th will shock you!");
        departmentNewsDao.add(otherDepartmentNews);
        Department testDepartment = setupDepartment();
        departmentDao.add(testDepartment);
        departmentDao.addDepartmentToDepartmentNewsArticle(testDepartment,testDepartmentNews);
        departmentDao.addDepartmentToDepartmentNewsArticle(testDepartment,otherDepartmentNews);
        assertEquals(2, departmentDao.getAllDepartmentNewsArticleForADepartment(testDepartment.getId()).size());
    }
    @Test
    public void deleteDepartmentAlsoUpdatesJoinTable() throws Exception {
        DepartmentNews testDepartmentNews  = new DepartmentNews("Nyama mama","The new restaurant in town making heads roll");
        departmentNewsDao.add(testDepartmentNews);
        Department testDepartment = setupDepartment();
        departmentDao.add(testDepartment);
        Department altDepartment = setupAltDepartment();
        departmentDao.add(altDepartment);
        departmentDao.addDepartmentToDepartmentNewsArticle(testDepartment,testDepartmentNews);
        departmentDao.addDepartmentToDepartmentNewsArticle(altDepartment, testDepartmentNews);
        departmentDao.deleteById(testDepartment.getId());
        assertEquals(0, departmentDao.getAllDepartmentNewsArticleForADepartment(testDepartment.getId()).size());
    }
}