package models;

import org.junit.Test;

import static org.junit.Assert.*;

public class DepartmentTest {
    //Helper method
    public Department setupDepartment(){ return new Department("Business", "Business news", 5);}

    @Test
    public void setId() {
        Department testDepartment = setupDepartment();
        testDepartment.setId(1);
        assertEquals(1, testDepartment.getId());
    }
    @Test
    public void setDescription() {
        Department testDepartment = setupDepartment();
        assertNotEquals("Sports news", testDepartment.getDescription());
    }
    @Test
    public void getDescription() {
        Department testDepartment = setupDepartment();
        assertEquals("Business news", testDepartment.getDescription());
    }
    @Test
    public void getTotal_employees() {
        Department testDepartment = setupDepartment();
        assertEquals(5, testDepartment.getTotal_employees());
    }
    @Test
    public void setTotal_employees() {
        Department testDepartment = setupDepartment();
        testDepartment.setTotal_employees(6);
        assertNotEquals(5, testDepartment.getTotal_employees());
    }
}