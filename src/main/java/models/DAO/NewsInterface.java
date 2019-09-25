package models.DAO;
import models.News;
import java.util.List;

public interface NewsInterface {
    void add(News news);

    List<News> getAll();

    News findById(int id);

    //update

    void deleteById(int id);

    void clearAll();

}
