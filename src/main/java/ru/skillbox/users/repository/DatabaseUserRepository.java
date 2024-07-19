package ru.skillbox.users.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.ArgumentPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.stereotype.Repository;
import ru.skillbox.users.model.User;
import ru.skillbox.users.model.UserRowMapper;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Primary
public class DatabaseUserRepository implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<User> findAll() {
        String sql = "SELECT * FROM users_schema.user";
        return jdbcTemplate.query(sql, new UserRowMapper());
    }

    @Override
    public Optional<User> findById(int id) {
        String sql = "SELECT * FROM users_schema.user WHERE id = ?";

        User user = DataAccessUtils.singleResult(
                jdbcTemplate.query(
                        sql,
                        new ArgumentPreparedStatementSetter(new Object[] {id}),
                        new RowMapperResultSetExtractor<>(new UserRowMapper())
                )
        );

        return Optional.ofNullable(user);
    }

    @Override
    public User save(User user) {
        String sql = "INSERT INTO users_schema.user (first_name, last_name, email, phone) VALUES (?, ?, ?, ?)";

        int id = jdbcTemplate.update(
                sql,
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPhone()
        );
        user.setId(id);

        return user;
    }

    @Override
    public User update(User user) {
        String sql = "UPDATE users_schema.user SET first_name = ?, last_name = ?, email = ?, phone = ? WHERE id = ?";

        jdbcTemplate.update(
                sql,
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPhone(),
                user.getId()
        );

        return user;
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM users_schema.user WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
