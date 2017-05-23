package ru.kpfu.itis.mustafin.controllers.forms;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import ru.kpfu.itis.mustafin.controllers.SimpleController;
import ru.kpfu.itis.mustafin.models.Cottage;
import ru.kpfu.itis.mustafin.services.CottageService;
import ru.kpfu.itis.mustafin.util.validation.CottageValidator;

import javax.annotation.PostConstruct;

public class CottageFormController extends SimpleController {

    @Autowired
    CottageValidator validator;

    private ObservableList<Cottage> cottageData;

    private Stage formStage;

    @Autowired
    CottageService service;

    @FXML
    TextField cottageNumberField;

    @FXML
    TextField bedsNumberField;

    @FXML
    CheckBox parkingCheckBox;

    @FXML
    CheckBox childZoneCheckBox;

    @FXML
    CheckBox animalsCheckBox;

    @FXML
    CheckBox gazeboCheckBox;

    @FXML
    TextField dailyRentField;

    private Cottage selectedCottage;

    @FXML
    public void initialize() {

    }

    @SuppressWarnings("unchecked")
    @PostConstruct
    public void init() {

    }

    public void submit(ActionEvent event) {
        try {
            if (selectedCottage == null) {
                Cottage cottage = new Cottage();
                configureObject(cottage);
                service.save(cottage);
                cottageData.add(cottage);
                formStage.close();
            } else {
                Cottage newCottage = new Cottage();
                newCottage.setId(selectedCottage.getId());
                configureObject(newCottage);
                service.save(newCottage);
                cottageData.set(cottageData.indexOf(selectedCottage), newCottage);
                selectedCottage = null;
                formStage.close();
            }
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, e.getMessage());
        }
    }

    private void configureObject(Cottage cottage) throws Exception {
        String yes = "имеется";
        String no = "не имеется";
        String permit = "разрешено";
        String deny = "запрещено";
        validator.validate(cottageNumberField.getText(), bedsNumberField.getText(), dailyRentField.getText());
        int cottageNumber = Integer.parseInt(cottageNumberField.getText());
        int bedsNumber = Integer.parseInt(bedsNumberField.getText());
        String parking = parkingCheckBox.isSelected() ? yes : no;
        String childZone = childZoneCheckBox.isSelected() ? yes : no;
        String animals = animalsCheckBox.isSelected() ? permit : deny;
        String gazebo = gazeboCheckBox.isSelected() ? yes : no;
        int dailyRent = Integer.parseInt(dailyRentField.getText());
        cottage.setBedsNumber(bedsNumber);
        cottage.setChildZone(childZone);
        cottage.setComeWithAnimals(animals);
        cottage.setCottageNumber(cottageNumber);
        cottage.setDailyRent(dailyRent);
        cottage.setGazebo(gazebo);
        cottage.setParking(parking);
    }

    public void fillFields() {
        cottageNumberField.setText(selectedCottage.getCottageNumber().toString());
        bedsNumberField.setText(selectedCottage.getBedsNumber().toString());
        parkingCheckBox.setSelected(selectedCottage.getParking().equals("имеется") ? true : false);
        gazeboCheckBox.setSelected(selectedCottage.getGazebo().equals("имеется") ? true : false);
        childZoneCheckBox.setSelected(selectedCottage.getChildZone().equals("имеется") ? true : false);
        animalsCheckBox.setSelected(selectedCottage.getComeWithAnimals().equals("разрешено") ? true : false);
        dailyRentField.setText(selectedCottage.getDailyRent().toString());
    }

    public void clearFields() {
        cottageNumberField.setText("");
        bedsNumberField.setText("");
        parkingCheckBox.setSelected(false);
        gazeboCheckBox.setSelected(false);
        childZoneCheckBox.setSelected(false);
        animalsCheckBox.setSelected(false);
        dailyRentField.setText("");
    }

    public void setFormStage(Stage formStage) {
        this.formStage = formStage;
    }

    public void setCottageData(ObservableList<Cottage> cottageData) {
        this.cottageData = cottageData;
    }

    public void setSelectedCottage(Cottage selectedCottage) {
        this.selectedCottage = selectedCottage;
    }

    public void close() {
        formStage.close();
    }

}
