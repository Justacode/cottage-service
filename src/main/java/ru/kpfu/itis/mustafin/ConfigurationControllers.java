package ru.kpfu.itis.mustafin;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.kpfu.itis.mustafin.controllers.AdminController;
import ru.kpfu.itis.mustafin.controllers.HomeController;
import ru.kpfu.itis.mustafin.controllers.LoginController;
import ru.kpfu.itis.mustafin.controllers.RegistrationController;
import ru.kpfu.itis.mustafin.controllers.forms.CottageFormController;
import ru.kpfu.itis.mustafin.controllers.forms.RentFormController;

import java.io.IOException;
import java.io.InputStream;

@Configuration
public class ConfigurationControllers {

    @Bean
    public LoginController getWelcomeController() throws IOException {
        return (LoginController) getLoginView().getController();
    }

    @Bean
    public RegistrationController getRegistrationController() throws IOException {
        return (RegistrationController) getRegistrationView().getController();
    }

    @Bean
    public HomeController getHomeController() throws IOException {
        return (HomeController) getHomeView().getController();
    }

    @Bean
    public RentFormController getRentFormController() throws IOException {
        return (RentFormController) getRentFormView().getController();
    }

    @Bean
    public AdminController getAdminController() throws IOException {
        return (AdminController) getAdminView().getController();
    }

    @Bean
    public CottageFormController getCottageFormController() throws IOException {
        return (CottageFormController) getCottageFormView().getController();
    }

    @Bean(name = "welcomeView")
    public View getLoginView() {
        return loadView("fxml/welcome.fxml");
    }

    @Bean(name = "registrationView")
    public View getRegistrationView() {
        return loadView("fxml/registration.fxml");
    }

    @Bean(name = "homeView")
    public View getHomeView() {
        return loadView("fxml/home.fxml");
    }

    @Bean(name = "rentFormView")
    public View getRentFormView() {
        return loadView("fxml/rentform.fxml");
    }

    @Bean(name = "adminView")
    public View getAdminView() {
        return loadView("fxml/admin.fxml");
    }

    @Bean(name = "cottageFormView")
    public View getCottageFormView() {
        return loadView("fxml/cottageform.fxml");
    }

    private View loadView(String url) {
        InputStream fxmlStream = null;
        try {
            try {
                fxmlStream = getClass().getClassLoader().getResourceAsStream(url);
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.load(fxmlStream);
                return new View(fxmlLoader.getRoot(), fxmlLoader.getController());
            } finally {
                if (fxmlStream != null) {
                    fxmlStream.close();
                }
            }
        } catch (IOException e) {
            System.err.println("FXML LOAD TROUBLE!!!");
            return null;
        }
    }

    public class View {
        private Parent view;
        private Object controller;

        public View(Parent view, Object controller) {
            this.view = view;
            this.controller = controller;
        }

        public Parent getView() {
            return view;
        }

        public void setView(Parent view) {
            this.view = view;
        }

        public Object getController() {
            return controller;
        }
    }
}
