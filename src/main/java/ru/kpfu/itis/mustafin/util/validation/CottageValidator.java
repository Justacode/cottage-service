package ru.kpfu.itis.mustafin.util.validation;

import org.springframework.stereotype.Component;

@Component
public class CottageValidator extends Validator {

    public void validate(String cottageNumber, String bedsNumber, String dailyRent) throws Exception {
        emptyCheck(cottageNumber, bedsNumber, dailyRent);
        numCheck(cottageNumber, bedsNumber, dailyRent);
    }
}
