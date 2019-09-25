package models;

public class Department {

    private int id;
    private String description;
    private int total_employees;
    private String news_link;
    private String department_type;
    private int user_id;

    public Department( String description, int total_employees, String news_link, String department_type,
                 int user_id) {
        this.description = description;
        this.total_employees = total_employees;
        this.news_link = news_link;
        this.department_type = department_type;
        this.user_id = user_id;
    }
    public int getId() { return id; }
    public String getFull_name() { return description; }
    public int getPosition() { return total_employees; }
    public String getNews_link() { return news_link; }
    public String getDepartment() { return department_type; }
    public int getUser_post() { return user_id; }
}
