package ru.skillbox.users.service;

import ru.skillbox.users.model.User;

import java.util.List;

public interface UserService {
    List<User> showAll();
    User show(int id);
    User create(User user);
    User update(User user);
    void delete(int id);
}
