package ru.iann.spring.storage;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.iann.spring.model.User;

import java.util.List;

@Repository
public class UserDaoDB implements DAO{

    private final JdbcTemplate template;

    public UserDaoDB(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public List<User> show() {
        return template.query("SELECT * FROM table11", new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public User getById(long id) {
        return template.query("SELECT * FROM table11 WHERE id=?", new Object[]{id},  new BeanPropertyRowMapper<>(User.class)).stream().findAny().orElse(null);
    }

    @Override
    public void create(User user) {
        template.update("INSERT INTO table11 (login, password, date) VALUES (?,?,?)",
                user.getLogin(), user.getPassword(), user.getDate());
    }

    @Override
    public void delete(long id) {
        template.update("DELETE FROM table11 WHERE id=?", id);
    }

    @Override
    public void update(long id, User user) {
        template.update("UPDATE table11 SET login=?, password=? WHERE id=?", user.getLogin(), user.getPassword(), id);
    }
}

