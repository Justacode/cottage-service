package ru.kpfu.itis.mustafin.util.validation;


import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;


public class Validator {

    public void emptyCheck(String... strings) throws AuthenticationException {
        for (String s : strings) {
            if (s.equals("") || s == null)
                throw new BadCredentialsException("Поля не должны быть пустыми!");
        }
    }

    public void numCheck(String... strings) throws Exception {
        try {
            for (String s : strings) {
                NumberUtils.isNumber(s);
            }
        } catch (Exception e) {
            throw new Exception("В полях для чисел введены не числовые значения!");
        }
    }
}
