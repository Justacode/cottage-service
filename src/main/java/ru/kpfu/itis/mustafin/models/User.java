package ru.kpfu.itis.mustafin.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table
public class User {

    @Id
    @GeneratedValue
    @Getter
    @Setter
    private Long id;

    @Column(unique = true)
    @Setter
    private String login;

    @Column
    @Setter
    private String password;

    @Column
    @Getter
    @Setter
    private String role;

    public User() {

    }

    public User(String login, String password, String role) {
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }
}
