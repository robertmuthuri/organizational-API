package models.DAO;

import models.User;

import java.util.List;

public interface UserInterface {

    void add(User news_post);

    User findById(int id);

    List<User> getAll();

    //update
    //omit for now

    void deleteById(int id);

    void clearAll();
}
