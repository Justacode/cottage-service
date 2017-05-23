package ru.kpfu.itis.mustafin.util.validation;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.kpfu.itis.mustafin.models.User;


@Component
public class LoginValidator extends Validator {

    private User realUser;

    private String checkLogin;

    private String checkPassword;

    public void validate(User user, String login, String password) throws BadCredentialsException {
        realUser = user;
        checkLogin = login;
        checkPassword = password;

        emptyCheck(checkLogin, checkPassword);
        userCheck();
        passwordCheck();
    }

    public void userCheck() throws AuthenticationException {
        if (realUser == null)
            throw new UsernameNotFoundException("Пользователь с таким логином не зарегистрирован!");
    }

    public void passwordCheck() throws AuthenticationException {
        String userPass = realUser.getPassword();
        if (!userPass.equals(checkPassword))
            throw new BadCredentialsException("Неверный пароль!");
    }
}
