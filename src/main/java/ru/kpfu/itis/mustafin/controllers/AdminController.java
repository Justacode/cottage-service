package ru.kpfu.itis.mustafin.controllers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.kpfu.itis.mustafin.ConfigurationControllers;
import ru.kpfu.itis.mustafin.controllers.forms.CottageFormController;
import ru.kpfu.itis.mustafin.controllers.forms.RentFormController;
import ru.kpfu.itis.mustafin.models.Cottage;
import ru.kpfu.itis.mustafin.models.Rent;
import ru.kpfu.itis.mustafin.services.CottageService;
import ru.kpfu.itis.mustafin.services.RentService;

import javax.annotation.PostConstruct;
import java.util.List;

public class AdminController extends SimpleController {

    @Autowired
    CottageService cottageService;

    @Autowired
    RentService rentService;

    @Autowired
    @Qualifier("welcomeView")
    ConfigurationControllers.View welcomeView;

    @Autowired
    @Qualifier("rentFormView")
    ConfigurationControllers.View rentFormView;

    @Autowired
    @Qualifier("cottageFormView")
    ConfigurationControllers.View cottageFormView;

    @FXML
    TableView<Cottage> cottageTable;

    @FXML
    TableView<Rent> rentTable;

    @FXML
    public void initialize() {

    }

    @SuppressWarnings("unchecked")
    @PostConstruct
    public void init() {
        List<Cottage> cottages = cottageService.getAll();
        initCottageTable(cottageTable, cottages);
        List<Rent> rents = rentService.getAll();
        initRentTable(rentTable, rents);
    }

    public void showCottageForm(ActionEvent event) {
        CottageFormController controller = (CottageFormController) cottageFormView.getController();
        controller.setCottageData(cottageTable.getItems());
        Stage formStage = createNewStage(cottageFormView, "Новый коттедж");
        controller.clearFields();
        controller.setFormStage(formStage);
        controller.setSelectedCottage(null);
        formStage.showAndWait();
    }

    @FXML
    public void deleteCottage() {
        Cottage cottage = cottageTable.getSelectionModel().getSelectedItem();
        if (cottage != null) {
            cottageService.delete(cottage);
            cottageTable.getItems().remove(cottageTable.getSelectionModel().getSelectedIndex());
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Вы не выбрали коттедж!");
            alert.show();
        }
    }

    public void showEditCottageForm(ActionEvent event) {
        Cottage cottage = cottageTable.getSelectionModel().getSelectedItem();
        if (cottage != null) {
            CottageFormController controller = (CottageFormController) cottageFormView.getController();
            controller.setCottageData(cottageTable.getItems());
            controller.setSelectedCottage(cottage);
            Stage formStage = createNewStage(cottageFormView, "Изменение коттеджа");
            controller.setFormStage(formStage);
            controller.fillFields();
            formStage.showAndWait();
        }else {
            showAlert(Alert.AlertType.ERROR, "Ничего не выбрано!");
        }
    }

    public void showRentForm(ActionEvent event) {
        RentFormController controller = (RentFormController) rentFormView.getController();
        controller.setRentData(rentTable.getItems());
        Stage formStage = createNewStage(rentFormView, "Новая аренда");
        controller.clearFields();
        controller.setSelectedRent(null);
        controller.setFormStage(formStage);
        formStage.showAndWait();
    }

    public void showEditRentForm(ActionEvent event) {
        Rent rent = rentTable.getSelectionModel().getSelectedItem();
        if (rent != null) {
            RentFormController controller = (RentFormController) rentFormView.getController();
            controller.setRentData(rentTable.getItems());
            controller.setSelectedRent(rent);
            Stage formStage = createNewStage(rentFormView, "Изменение аренды");
            controller.setFormStage(formStage);
            controller.fillFields();
            formStage.showAndWait();
        }else {
            showAlert(Alert.AlertType.ERROR, "Ничего не выбрано!");
        }
    }

    @FXML
    public void deleteRent() {
        Rent rent = rentTable.getSelectionModel().getSelectedItem();
        if (rent != null) {
            rentService.delete(rent);
            rentTable.getItems().remove(rentTable.getSelectionModel().getSelectedIndex());
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Вы не выбрали аренду!");
            alert.show();
        }
    }

    public void backToWelcome(ActionEvent event) {
        SecurityContextHolder.clearContext();
        changeSceneByEvent(event, welcomeView, "Сервис аренды коттеджей");
    }

    public void refreshRentTable(){
        List<Rent> rents = rentService.getAll();
        rentTable.setItems(FXCollections.observableArrayList(rents));
    }

    public TableView<Rent> getRentTable() {
        return rentTable;
    }
}
