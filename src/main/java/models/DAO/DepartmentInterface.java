package models.DAO;

import models.Department;
import models.DepartmentNews;
import models.User;

import java.util.List;

public interface DepartmentInterface {

    void add(Department department);

    Department findById(int id);

    List<Department> getAll();

    void addDepartmentToDepartmentNewsArticle(Department department, DepartmentNews departmentNews);

    List<DepartmentNews> getAllDepartmentNewsArticleForADepartment(int department_id);

    //update

    void deleteById(int id);

    void clearAll();
}
