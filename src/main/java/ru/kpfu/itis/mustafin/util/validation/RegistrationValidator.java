package ru.kpfu.itis.mustafin.util.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kpfu.itis.mustafin.services.UserService;

@Component
public class RegistrationValidator extends Validator {

    @Autowired
    UserService userService;

    public void validate(String login, String password, String confirmation) throws Exception {
        spaceCheck(login, password);
        super.emptyCheck(login, password, confirmation);
        confirmationCheck(password, confirmation);
        loginUniqueCheck(login);
    }

    private void spaceCheck(String login, String password) throws Exception {
        if (login.contains(" ") || password.contains(" "))
            throw new Exception("Поля не должны содержать пробелов!");
    }

    private void confirmationCheck(String password, String confirmation) throws Exception {
        if (!password.equals(confirmation))
            throw new Exception("Пароли не совпадают!");
    }

    private void loginUniqueCheck(String login) throws Exception {
        if (userService.getByLogin(login) != null)
            throw new Exception("Пользователь с данным логином уже существует!");
    }
}
