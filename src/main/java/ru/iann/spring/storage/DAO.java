package ru.iann.spring.storage;

import ru.iann.spring.model.User;

import java.util.List;

public interface DAO {
    List<User> show();
    User getById(long id);
    void create(User user);
    void delete(long id);
    void update(long id, User user);

}
