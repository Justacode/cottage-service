package ru.kpfu.itis.mustafin.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kpfu.itis.mustafin.models.User;
import ru.kpfu.itis.mustafin.repositories.UserRepository;
import ru.kpfu.itis.mustafin.services.UserService;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @PostConstruct
    public void generateTestData() {
        save(new User("admin", "123321", "ADMIN_ROLE"));
        save(new User("petya", "qwerty", "USER_ROLE"));
    }

    @Override
    public User save(User user) {
        return userRepository.saveAndFlush(user);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User getByLogin(String login) {
        return userRepository.findByLogin(login);
    }

}
