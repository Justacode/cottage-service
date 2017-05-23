package ru.kpfu.itis.mustafin.controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.kpfu.itis.mustafin.ConfigurationControllers;
import ru.kpfu.itis.mustafin.util.validation.LoginValidator;

import java.util.List;


public class LoginController extends SimpleController {

    @Autowired
    AuthenticationManager authenticator;

    @Autowired
    @Qualifier("homeView")
    ConfigurationControllers.View homeView;

    @Autowired
    @Qualifier("registrationView")
    ConfigurationControllers.View registrationView;

    @Autowired
    @Qualifier("adminView")
    ConfigurationControllers.View adminView;

    @FXML
    TextField login;

    @FXML
    PasswordField password;

    @FXML
    public void initialize() {
    }

    @FXML
    public void signIn(ActionEvent event) {
        try {
            String enteredLogin = login.getText();
            String enteredPassword = password.getText();
            Authentication request = new UsernamePasswordAuthenticationToken(enteredLogin, enteredPassword);
            Authentication token = authenticator.authenticate(request);
            SecurityContextHolder.getContext().setAuthentication(token);
            if (userHasAuthority("ADMIN_ROLE")) {
                AdminController adminController = (AdminController) adminView.getController();
                adminController.refreshRentTable();
                changeSceneByEvent(event, adminView, "Сервис аренды коттеджей(режим администратора)");
            } else{
                changeSceneByEvent(event, homeView, "Сервис аренды коттеджей");
            }
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, e.getMessage());
        }
    }

    public static boolean userHasAuthority(String authority) {
        List<GrantedAuthority> authorities = (List<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        for (GrantedAuthority grantedAuthority : authorities) {
            if (authority.equals(grantedAuthority.getAuthority())) {
                return true;
            }
        }
        return false;
    }

    @FXML
    public void signUp(ActionEvent event) {
        changeSceneByEvent(event, registrationView, "Регистрация");
    }
}
