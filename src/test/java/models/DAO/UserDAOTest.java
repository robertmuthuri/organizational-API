package models.DAO;
import models.User;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class UserDAOTest {
    private UserDAO userDao;
    private Connection conn;

    @Before
    public void setUp() throws Exception {
    String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
    Sql2o sql2o = new Sql2o(connectionString, "", "");
    userDao = new UserDAO(sql2o);
    conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }
    //helper method
    public User setupNewUser() {
        return new User("John","Editor","Approves news edits",1);
    }

    @Test
    public void addingNewUserSetsId_true() throws Exception {
        User testUser = setupNewUser();
        int originalUserId = testUser.getId();
        userDao.add(testUser);
        assertNotEquals(originalUserId, testUser.getId());
    }
    @Test
    public void addedUsersAreReturnedUsingGetAll() throws Exception {
        User testUser = setupNewUser();
        userDao.add(testUser);
        assertEquals(1, userDao.getAll().size());
    }
    @Test
    public void noUsersReturnsEmptyList() throws Exception {
        assertEquals(0, userDao.getAll().size());
    }
    @Test
    public void deleteByIdDeletesCorrectUser() throws Exception {
        User user = setupNewUser();
        userDao.add(user);
        userDao.deleteById(user.getId());
        assertEquals(0, userDao.getAll().size());
    }
    @Test
    public void clearAll() throws Exception {
        User testUser = setupNewUser();
        User otherUser = setupNewUser();
        userDao.clearAll();
        assertEquals(0, userDao.getAll().size());
    }
}