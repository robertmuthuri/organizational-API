package models;

public class News {
    public int id;
    public String heading;
    public String news_link;
    public String news_type;
    public static final String TYPE = "general_news";

    public News(String heading, String news_link) {
        this.heading = heading;
        this.news_link = news_link;
        this.news_type = TYPE;
    }

    public int getId() { return id; }
    public String getHeading() { return heading; }
    public String getNews_link() { return news_link; }
    public String getNews_type() { return news_type; }
}
