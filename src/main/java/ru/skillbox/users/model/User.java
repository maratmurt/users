package ru.skillbox.users.model;

import lombok.Data;

import java.util.Objects;

@Data
public class User {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(email, user.email) || Objects.equals(phone, user.phone);
    }
}
