package ru.skillbox.users.repository;

import ru.skillbox.users.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    List<User> findAll();
    Optional<User> findById(int id);
    User save(User user);
    User update(User user);
    void deleteById(int id);
}
