package models;

public class DepartmentNews extends News {

    public static final String TYPE = "department_news";

    public DepartmentNews(String heading, String news_link) {
        super(heading, news_link);
        this.news_type = TYPE;
    }

}
