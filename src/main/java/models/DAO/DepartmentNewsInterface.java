package models.DAO;
import models.Department;
import models.DepartmentNews;
import org.graalvm.compiler.lir.LIR;

import java.util.List;

public interface DepartmentNewsInterface {

    void add(DepartmentNews departmentNews);

    DepartmentNews findById(int id);

    void addDepartmentNewsToDepartment(DepartmentNews departmentNews, Department department);

    List<Department> getAllDepartmentsForADepartmentNewsArticle(int id);

    List<DepartmentNews> getAll();

    //Update

    void deleteById(int id);

    void clearAll();

}
