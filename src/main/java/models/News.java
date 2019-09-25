package models;

public class News {
    private int id;
    private String heading;
    private String news_link;
    private String news_type;
    public static final String TYPE = "general_news";

    public News(String heading, String news_link, String news_type) {
        this.heading = heading;
        this.news_link = news_link;
        this.news_type = TYPE;
    }

    public int getId() { return id; }
    public String getHeading() { return heading; }
    public String getNews_link() { return news_link; }
    public String getNews_type() { return news_type; }
}
