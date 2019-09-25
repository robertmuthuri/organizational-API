package models.DAO;
import models.*;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.ArrayList;
import java.util.List;

public class DepartmentDAO implements DepartmentInterface {
    private final Sql2o sql2o;
    public DepartmentDAO(Sql2o sql2o){ this.sql2o = sql2o; }

    @Override
    public void add(Department department) {
        String sql = "INSERT INTO departments (name, description, total_employees) VALUES (:name, :description, :total_employees)";
        try(Connection con = sql2o.open()){
            int id = (int) con.createQuery(sql, true)
                    .bind(department)
                    .executeUpdate()
                    .getKey();
            department.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }
    @Override
    public Department findById(int id) {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM departments WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(Department.class);
        }
    }
    @Override
    public List<Department> getAll() {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM departments")
                    .executeAndFetch(Department.class);
        }
    }
    @Override
    public void deleteById(int id) {
        String sql = "DELETE from departments WHERE id=:id";
        String joinSql = "DELETE from departments_departmentnews WHERE department_id = :department_id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
            con.createQuery(joinSql)
                    .addParameter("department_id", id)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }
    @Override
    public void clearAll() {
        String sql = "DELETE from departments";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql).executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }
    @Override
    public void addDepartmentToDepartmentNewsArticle(Department department, DepartmentNews departmentnews){
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
    public List<DepartmentNews> getAllDepartmentNewsArticleForADepartment(int department_id) {

        ArrayList<DepartmentNews> departmentnews = new ArrayList<>();

        String joinQuery = "SELECT departmentnews_id FROM departments_departmentnews WHERE department_id = :department_id";

        try (Connection con = sql2o.open()) {
            List<Integer> allDepartmentNewsIds = con.createQuery(joinQuery)
                    .addParameter("department_id", department_id)
                    .executeAndFetch(Integer.class);
            for (Integer departmentnewsId : allDepartmentNewsIds){
                String scopedarticleQuery = "SELECT * FROM scoped_articles WHERE id = :departmentnews_id";
                departmentnews.add(
                        con.createQuery(scopedarticleQuery)
                                .addParameter("departmentnews_id", departmentnewsId)
                                .executeAndFetchFirst(DepartmentNews.class));
            } //why are we doing a second sql query - set?
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
        return departmentnews;
    }
}
