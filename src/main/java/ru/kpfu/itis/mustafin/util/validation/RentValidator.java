package ru.kpfu.itis.mustafin.util.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kpfu.itis.mustafin.models.Cottage;
import ru.kpfu.itis.mustafin.services.CottageService;
import ru.kpfu.itis.mustafin.services.RentService;

import java.time.LocalDate;

@Component
public class RentValidator extends Validator{

    @Autowired
    CottageService cottageService;

    public void validate(String name, String phonenumber, String cottageNumber, LocalDate arrival, LocalDate departure) throws Exception {
        emptyCheck(name, phonenumber, cottageNumber);
        numCheck(phonenumber, cottageNumber);
        cottageNumberCheck(cottageNumber);
        checkDate(arrival, departure);
    }

    private void cottageNumberCheck(String cottageNumber) throws Exception {
        if(cottageService.getByCottageNumber(Integer.parseInt(cottageNumber))==null){
            throw new Exception("Указанного коттеджа нет в списке!");
        }
    }

    private void checkDate(LocalDate arrival, LocalDate departure) throws Exception {
        if (arrival==null||departure==null){
            throw new Exception("Поля дат не должны быть пустыми!");
        }
        if (arrival.isBefore(LocalDate.now())){
            throw new Exception("Дата прибытия не может быть меньше текущей");
        }
        if(departure.isBefore(arrival)||departure.isEqual(arrival)){
            throw new Exception("Некоректно указаны даты приезда и отъезда");
        }
    }
}
