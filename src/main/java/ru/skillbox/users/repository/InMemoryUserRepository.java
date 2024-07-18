package ru.skillbox.users.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import ru.skillbox.users.model.User;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
@RequiredArgsConstructor
@Slf4j
public class InMemoryUserRepository implements UserRepository {

    private final Set<User> users;
    private int count;

    @Override
    public List<User> findAll() {
        return users.stream().toList();
    }

    @Override
    public Optional<User> findById(int id) {
        return users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();
    }

    @Override
    public User save(User user) {
        user.setId(count++);
        if (!users.add(user)) {
            throw new RuntimeException("User with this data already exists");
        }
        return user;
    }

    @Override
    public User update(User user) {
        User userToUpdate = users.stream()
                .filter(existing -> existing.getId().equals(user.getId()))
                .findFirst()
                .orElseThrow();
        users.remove(userToUpdate);
        users.add(user);
        return user;
    }

    @Override
    public void deleteById(int id) {
        User userToDelete = users.stream()
                .filter(existing -> existing.getId().equals(id))
                .findFirst()
                .orElseThrow();
        users.remove(userToDelete);
    }
}
