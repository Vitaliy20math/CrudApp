package web.service;

import web.models.User;

import java.util.List;

public interface UserService {
    List<User> listAllUsers();
    User getUser(int id);
    void save(User user);
    void update(User user);
    void delete(int id);
}
