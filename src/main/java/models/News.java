package models;

import java.util.Objects;

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

    public void setId(int id) { this.id = id; }
    public void setHeading(String heading) { this.heading = heading; }
    public void setNews_link(String news_link) { this.news_link = news_link; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        News news = (News) o;
        return getId() == news.getId() &&
                Objects.equals(getHeading(), news.getHeading()) &&
                Objects.equals(getNews_link(), news.getNews_link()) &&
                Objects.equals(getNews_type(), news.getNews_type());
    }
    @Override
    public int hashCode() {
        return Objects.hash(getId(), getHeading(), getNews_link(), getNews_type());
    }
}
