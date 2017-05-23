package ru.kpfu.itis.mustafin.services;

import ru.kpfu.itis.mustafin.models.User;

import java.util.List;

public interface UserService {

    public User save (User user);

    public List<User> getAll();

    public User getByLogin (String login);
}
