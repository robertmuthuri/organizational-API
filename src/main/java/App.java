import com.google.gson.Gson;
import models.*;
import models.DAO.*;
import org.sql2o.*;
import static spark.Spark.*;
import exceptions.ApiException;

public class App {
    public static void main(String[] args) {
        DepartmentNewsDAO departmentNewsDAO;
        NewsDAO newsDAO;
        DepartmentDAO departmentDAO;
        UserDAO userDAO;
        Connection conn;
        Gson gson = new Gson();

        String connectionString = "jdbc:h2:~/organisationalapi.db;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        departmentDAO = new DepartmentDAO(sql2o);
        newsDAO = new NewsDAO(sql2o);
        departmentNewsDAO = new DepartmentNewsDAO(sql2o);
        userDAO = new UserDAO(sql2o);
        conn = sql2o.open();

        //Creates a department
        post("/departments/new", "application/json", (req, res) -> {
            Department department = gson.fromJson(req.body(), Department.class);
            departmentDAO.add(department);
            res.status(201);
            return gson.toJson(department);
        });
        //Reads from departments
        get("/departments", "application/json", (req, res) -> {
            return gson.toJson(departmentDAO.getAll());
        });
        //Gets a specific department
        get("/departments/:id", "application/json", (req, res) -> {
            return gson.toJson(departmentDAO.findById(Integer.parseInt(req.params("id"))));
        });
        //Post department news
        post("/departmentnews/new", "application/json", (req, res) -> {
            DepartmentNews departmentNews  = gson.fromJson(req.body(), DepartmentNews.class);
            departmentNewsDAO.add(departmentNews);
            res.status(201);
            return gson.toJson(departmentNews);
        });

        // post users
        post("/users/new", "application/json", (req, res) -> {
            User user  = gson.fromJson(req.body(), User.class);
            userDAO.add(user);
            res.status(201);
            return gson.toJson(user);
        });
        //get user
        get("/users", "application/json", (req, res) -> {
            return gson.toJson(userDAO.getAll());
        });
        //get user by id
        get("/users/:id", "application/json", (req, res) -> {
            return gson.toJson(userDAO.findById(Integer.parseInt(req.params("id"))));
        });
        //post general news
        post("/news/new", "application/json", (req, res) -> {
            News news  = gson.fromJson(req.body(), News.class);
            newsDAO.add(news);
            res.status(201);
            return gson.toJson(news);
        });
        //Read general news
        get("/news", "application/json", (req, res) -> {
            return gson.toJson(newsDAO.getAll());
        });
        // Read  general news by ID
        get("/news/:id", "application/json", (req, res) -> {
            DepartmentNews departmentNewsToFind = departmentNewsDAO.findById(Integer.parseInt(req.params("id")));
            if (departmentNewsToFind == null){
                throw new ApiException(404, String.format("No departmentnews with the id: \"%s\" exists", req.params("id")));
            }
            else {
                return gson.toJson(departmentNewsToFind);
            }
        });
        // post department news
        post("/departmentnews/new", "application/json", (req, res) -> {
            DepartmentNews departmentNews  = gson.fromJson(req.body(), DepartmentNews.class);
            departmentNewsDAO.add(departmentNews);
            res.status(201);
            return gson.toJson(departmentNews);
        });
        // gets department news
        get("/departmentnews", "application/json", (req, res) -> {
            return gson.toJson(departmentNewsDAO.getAll());
        });
        // get department news by ID
        get("/departmentnews/:id", "application/json", (req, res) -> {
            return gson.toJson(departmentNewsDAO.findById(Integer.parseInt(req.params("id"))));
        });
        //Gets department with its news
        post("/departments/:departmentId/departmentnews/:departmentnewsId", "application/json", (req, res) -> {
            int departmentId = Integer.parseInt(req.params("departmentId"));
            int departmentnewsId = Integer.parseInt(req.params("departmentnewsId"));
            Department department = departmentDAO.findById(departmentId);
            DepartmentNews departmentNews = departmentNewsDAO.findById(departmentnewsId);

            if (department != null && departmentNews != null){
                departmentNewsDAO.addDepartmentNewsToDepartment(departmentNews, department);
                res.status(201);
                return gson.toJson(String.format("Department '%s' and DepartmentNews '%s' have been associated",departmentNews.getHeading(), department.getName()));
            }
            else {
                throw new ApiException(404, String.format("Department or DepartmentNews does not exist"));
            }
        });
        //
        get("/departments/:id/departmentnews", "application/json", (req, res) -> {
            int departmentId = Integer.parseInt(req.params("id"));
            Department departmentToFind = departmentDAO.findById(departmentId);
            if (departmentToFind == null){
                throw new ApiException(404, String.format("No department with the id: \"%s\" exists", req.params("id")));
            }
            else if (departmentDAO.getAllDepartmentNewsArticleForADepartment(departmentId).size()==0){
                return "{\"message\":\"I'm sorry, but no departmentnews are listed for this department.\"}";
            }
            else {
                return gson.toJson(departmentDAO.getAllDepartmentNewsArticleForADepartment(departmentId));
            }
        });
        //
        get("/departmentnews/:id/departments", "application/json", (req, res) -> {
            int departmentNewsId = Integer.parseInt(req.params("id"));
            DepartmentNews departmentNewsToFind = departmentNewsDAO.findById(departmentNewsId);
            if (departmentNewsToFind == null){
                throw new ApiException(404, String.format("No departmentnews with the id: \"%s\" exists", req.params("id")));
            }
            else if (departmentNewsDAO.getAllDepartmentsForADepartmentNewsArticle(departmentNewsId).size()==0){
                return "{\"message\":\"I'm sorry, but no departments are listed for this department news.\"}";
            }
            else {
                return gson.toJson(departmentNewsDAO.getAllDepartmentsForADepartmentNewsArticle(departmentNewsId));
            }
        });

        // Filter
        after((req, res) ->{
            res.type("application/json");
        });
    }
}
