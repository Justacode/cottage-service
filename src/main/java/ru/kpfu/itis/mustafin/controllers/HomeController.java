package ru.kpfu.itis.mustafin.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.kpfu.itis.mustafin.ConfigurationControllers;
import ru.kpfu.itis.mustafin.controllers.forms.RentFormController;
import ru.kpfu.itis.mustafin.models.Cottage;
import ru.kpfu.itis.mustafin.services.CottageService;

import javax.annotation.PostConstruct;
import java.util.List;

public class HomeController extends SimpleController {

    @Autowired
    CottageService cottageService;

    @Autowired
    @Qualifier("welcomeView")
    ConfigurationControllers.View welcomeView;

    @Autowired
    @Qualifier("rentFormView")
    ConfigurationControllers.View rentFormView;

    @Autowired
    @Qualifier("adminView")
    ConfigurationControllers.View adminView;

    @FXML
    TableView<Cottage> cottageTable;

    @FXML
    public void initialize() {

    }

    @SuppressWarnings("unchecked")
    @PostConstruct
    public void init() {
        List<Cottage> cottages = cottageService.getAll();
        initCottageTable(cottageTable, cottages);
    }

    public void backToWelcome(ActionEvent event) {
        SecurityContextHolder.clearContext();
        changeSceneByEvent(event, welcomeView, "Сервис аренды коттеджей");
    }

    public void showRentForm(ActionEvent event) {
        RentFormController controller = (RentFormController) rentFormView.getController();
        Stage formStage = createNewStage(rentFormView, "Создание новой аренды");
        controller.setFormStage(formStage);
        controller.clearFields();
        AdminController adm = (AdminController) adminView.getController();
        controller.setRentData(adm.getRentTable().getItems());
        formStage.showAndWait();
    }
}
