package ru.kpfu.itis.mustafin.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import ru.kpfu.itis.mustafin.ConfigurationControllers;
import ru.kpfu.itis.mustafin.controllers.forms.RentFormController;
import ru.kpfu.itis.mustafin.models.Cottage;
import ru.kpfu.itis.mustafin.models.Rent;

import java.util.List;


public abstract class SimpleController {

    protected void changeSceneByEvent(ActionEvent event, ConfigurationControllers.View view, String name) {
        Parent page = view.getView();
        Scene scene = page.getScene() == null ? new Scene(page) : page.getScene();
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.hide();
        appStage.setScene(scene);
        appStage.setTitle(name);
        appStage.show();
    }

    protected Stage createNewStage(ConfigurationControllers.View view, String name) {
        Parent page = view.getView();
        Scene scene = view.getView().getScene();
        if (scene == null) {
            scene = new Scene(page);
        }
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setScene(scene);
        stage.setTitle(name);
        stage.initModality(Modality.WINDOW_MODAL);
        return stage;
    }

    protected void showAlert(Alert.AlertType type, String message) {
        Alert error = new Alert(type, message);
        error.show();
    }

    protected void initCottageTable(TableView<Cottage> tableView, List<Cottage> cottages) {
        ObservableList<Cottage> cottageData = FXCollections.observableArrayList(cottages);

        TableColumn<Cottage, Integer> cottageNum = new TableColumn<>("Номер");
        cottageNum.setCellValueFactory(new PropertyValueFactory<>("cottageNumber"));

        TableColumn<Cottage, String> bedsNum = new TableColumn<>("Кол-во спальных мест");
        bedsNum.setCellValueFactory(new PropertyValueFactory<>("bedsNumber"));

        TableColumn<Cottage, String> parking = new TableColumn<>("Парковочное место");
        parking.setCellValueFactory(new PropertyValueFactory<>("parking"));

        TableColumn<Cottage, String> childrenZone = new TableColumn<>("Детская зона");
        childrenZone.setCellValueFactory(new PropertyValueFactory<>("childZone"));

        TableColumn<Cottage, String> gazebo = new TableColumn<>("Беседка");
        gazebo.setCellValueFactory(new PropertyValueFactory<>("gazebo"));

        TableColumn<Cottage, String> animals = new TableColumn<>("Въезд с животными");
        animals.setCellValueFactory(new PropertyValueFactory<>("comeWithAnimals"));

        TableColumn<Cottage, String> rent = new TableColumn<>("Стоимость аренды(сут.)");
        rent.setCellValueFactory(new PropertyValueFactory<>("dailyRent"));

        tableView.getColumns().setAll(cottageNum, bedsNum, parking, childrenZone, gazebo, animals, rent);

        tableView.setItems(cottageData);
    }

    protected void initRentTable(TableView<Rent> tableView, List<Rent> rents){
        ObservableList<Rent> rentData = FXCollections.observableArrayList(rents);

        TableColumn<Rent, String> fullName = new TableColumn<>("Полное имя");
        fullName.setCellValueFactory(new PropertyValueFactory<>("fullName"));

        TableColumn<Rent, String> phoneNumber = new TableColumn<>("Номер телефона");
        phoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));

        TableColumn<Rent, Integer> cottageNumber = new TableColumn<>("Номер коттеджа");
        cottageNumber.setCellValueFactory(new PropertyValueFactory<>("cottageNumber"));

        TableColumn<Rent, String> arrival = new TableColumn<>("Дата въезда");
        arrival.setCellValueFactory(new PropertyValueFactory<>("arrival"));

        TableColumn<Rent, String> departure = new TableColumn<>("Дата выезда");
        departure.setCellValueFactory(new PropertyValueFactory<>("departure"));

        tableView.getColumns().setAll(fullName, phoneNumber, cottageNumber, arrival, departure);

        tableView.setItems(rentData);
    }
}
