package ru.kpfu.itis.mustafin;

import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Lazy;

@Lazy
@SpringBootApplication
public class CottageApplication extends AbstractJavaFxApplicationSupport {

    @Value("Сервис аренды коттеджей")
    private String windowTitle;

    @Qualifier("welcomeView")
    @Autowired
    ConfigurationControllers.View welcomeView;


    public static void main(String[] args) {
        launchApp(CottageApplication.class, args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle(windowTitle);
        stage.setScene(new Scene(welcomeView.getView()));
        stage.setResizable(true);
        stage.centerOnScreen();
        stage.show();
    }
}
