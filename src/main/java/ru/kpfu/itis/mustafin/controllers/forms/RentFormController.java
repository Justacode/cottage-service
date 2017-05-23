package ru.kpfu.itis.mustafin.controllers.forms;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import ru.kpfu.itis.mustafin.controllers.SimpleController;
import ru.kpfu.itis.mustafin.models.Rent;
import ru.kpfu.itis.mustafin.services.RentService;
import ru.kpfu.itis.mustafin.util.validation.RentValidator;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RentFormController extends SimpleController {

    private ObservableList<Rent> rentData;

    private Stage formStage;

    @Autowired
    RentService service;

    @FXML
    TextField fullNameField;

    @FXML
    TextField phoneNumberField;

    @FXML
    TextField cottageNumberField;

    @FXML
    DatePicker arrivalField;

    @FXML
    DatePicker departureField;

    private Rent selectedRent;

    @Autowired
    private RentValidator validator;

    @FXML
    public void initialize() {

    }

    @SuppressWarnings("unchecked")
    @PostConstruct
    public void init() {

    }

    public void submit(ActionEvent event) {
        try {
            if (selectedRent == null) {
                Rent rent = new Rent();
                configureObject(rent);
                service.save(rent);
                rentData.add(rent);
                formStage.close();
                String message = "Заявка на коттедж успешно оформлена!";
                showAlert(Alert.AlertType.INFORMATION, message);
            } else {
                rentData.remove(selectedRent);
                Rent newRent = new Rent();
                newRent.setId(selectedRent.getId());
                configureObject(selectedRent);
                service.save(selectedRent);
                rentData.add(selectedRent);
                selectedRent = null;
                formStage.close();
            }
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, e.getMessage());
        }
    }

    public void configureObject(Rent rent) throws Exception {
        String fullName = fullNameField.getText();
        String phoneNumber = phoneNumberField.getText();
        LocalDate arrival = arrivalField.getValue();
        LocalDate departure = departureField.getValue();
        validator.validate(fullName, phoneNumber, cottageNumberField.getText(), arrival, departure);
        int cottageNumber = Integer.parseInt(cottageNumberField.getText());
        rent.setArrival(arrival.toString());
        rent.setCottageNumber(cottageNumber);
        rent.setDeparture(departure.toString());
        rent.setFullName(fullName);
        rent.setPhoneNumber(phoneNumber);
    }

    public void fillFields() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        cottageNumberField.setText(selectedRent.getCottageNumber().toString());
        phoneNumberField.setText(selectedRent.getPhoneNumber().toString());
        fullNameField.setText(selectedRent.getFullName());
        arrivalField.setValue(LocalDate.parse(selectedRent.getArrival(), dtf));
        departureField.setValue(LocalDate.parse(selectedRent.getDeparture(), dtf));
    }

    public void clearFields() {
        cottageNumberField.setText("");
        fullNameField.setText("");
        phoneNumberField.setText("");
        arrivalField.setValue(null);
        departureField.setValue(null);
    }

    public void close(ActionEvent event) {
        formStage.close();
    }

    public void setFormStage(Stage formStage) {
        this.formStage = formStage;
    }

    public void setRentData(ObservableList<Rent> rentData) {
        this.rentData = rentData;
    }

    public void setSelectedRent(Rent selectedRent) {
        this.selectedRent = selectedRent;
    }
}
