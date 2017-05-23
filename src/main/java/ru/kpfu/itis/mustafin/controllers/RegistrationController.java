package ru.kpfu.itis.mustafin.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.kpfu.itis.mustafin.ConfigurationControllers;
import ru.kpfu.itis.mustafin.models.User;
import ru.kpfu.itis.mustafin.security.Authenticator;
import ru.kpfu.itis.mustafin.services.impl.UserServiceImpl;
import ru.kpfu.itis.mustafin.util.validation.RegistrationValidator;


public class RegistrationController extends SimpleController {

    @Autowired
    @Qualifier("welcomeView")
    ConfigurationControllers.View welcomeView;

    @Autowired
    @Qualifier("homeView")
    ConfigurationControllers.View homeView;

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField passwordConfirmationField;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private RegistrationValidator validator;

    @Autowired
    Authenticator authenticator;

    @FXML
    public void initialize() {
    }

    @FXML
    public void registrate(ActionEvent event) {
        try {
            String login = loginField.getText();
            String pass = passwordField.getText();
            String confrimation = passwordConfirmationField.getText();

            validator.validate(login, pass, confrimation);
            User saved = userService.save(new User(login, pass, "USER_ROLE"));
            Authentication request = new UsernamePasswordAuthenticationToken(login, pass);
            Authentication token = authenticator.authenticate(request);
            SecurityContextHolder.getContext().setAuthentication(token);

            changeSceneByEvent(event, homeView, "Сервис аренды коттеджей");
            String successMessage = "Вы успешно зарегистрированы! Теперь вы можете воспользоваться сервисом!";
            showAlert(Alert.AlertType.INFORMATION, successMessage);

        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, e.getMessage());
        }

    }

    public void backToLogin(ActionEvent event) {
        changeSceneByEvent(event, welcomeView, "Сервис аренды коттеджей");
    }
}
