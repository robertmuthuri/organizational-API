package models.DAO;

import models.User;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;
import java.util.List;

public class UserDAO implements UserInterface {

    private final Sql2o sql2o;
    public UserDAO(Sql2o sql2o){ this.sql2o = sql2o; }

    @Override
    public void add(User user) {
        String sql = "INSERT INTO users (full_name, position, role, department_id) VALUES (:full_name, :position, :role, :department_id)";
        try(Connection con = sql2o.open()){
            int id = (int) con.createQuery(sql, true)
                    .bind(user)
                    .executeUpdate()
                    .getKey();
            user.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

}
