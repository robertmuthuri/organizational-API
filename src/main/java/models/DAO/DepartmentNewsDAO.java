package models.DAO;

import models.Department;
import models.DepartmentNews;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.ArrayList;
import java.util.List;

public class DepartmentNewsDAO implements DepartmentNewsInterface {
    private final Sql2o sql2o;
    public DepartmentNewsDAO(Sql2o sql2o){ this.sql2o = sql2o; }

    @Override
    public void add(DepartmentNews departmentnews) {
        String sql = "INSERT INTO department_news (heading, news_link) VALUES (:heading, :news_link)";
        try(Connection con = sql2o.open()){
            int id = (int) con.createQuery(sql, true)
                    .bind(departmentnews)
                    .executeUpdate()
                    .getKey();
            departmentnews.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }
    @Override
    public DepartmentNews findById(int id) {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM department_news WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(DepartmentNews.class);
        }
    }
    @Override
    public List<DepartmentNews> getAll() {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM department_news")
                    .executeAndFetch(DepartmentNews.class);
        }
    }
    @Override
    public void deleteById(int id) {
        String sql = "DELETE from department_news WHERE id=:id";
        String joinSql = "DELETE from departments_departmentnews WHERE departmentnews_id=:id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
            con.createQuery(joinSql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }
    @Override
    public void clearAll() {
        String sql = "DELETE from department_news";
        String joinSql = "DELETE from departments_departmentnews";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql).executeUpdate();
            con.createQuery(joinSql).executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }
    @Override
    public void addDepartmentNewsToDepartment(DepartmentNews departmentnews, Department department){
        String sql = "INSERT INTO departments_departmentnews (department_id, departmentnews_id) VALUES (:department_id, :departmentnews_id)";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("department_id", department.getId())
                    .addParameter("departmentnews_id", departmentnews.getId())
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }
    @Override
    public List<Department> getAllDepartmentsForADepartmentNewsArticle(int departmentnews_id) {

        ArrayList<Department> departments = new ArrayList<>();

        String joinQuery = "SELECT department_id FROM departments_departmentnews WHERE departmentnews_id = :departmentnews_id";

        try (Connection con = sql2o.open()) {
            List<Integer> allDepartmentIds = con.createQuery(joinQuery)
                    .addParameter("departmentnews_id", departmentnews_id)
                    .executeAndFetch(Integer.class); //what is happening in the lines above?
            for (Integer department_id : allDepartmentIds){
                String departmentQuery = "SELECT * FROM departments WHERE id = :department_id";
                departments.add(
                        con.createQuery(departmentQuery)
                                .addParameter("department_id", department_id)
                                .executeAndFetchFirst(Department.class));
            } //why are we doing a second sql query - set?
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
        return departments;
    }
}
