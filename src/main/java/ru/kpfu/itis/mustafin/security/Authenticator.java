package ru.kpfu.itis.mustafin.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import ru.kpfu.itis.mustafin.models.User;
import ru.kpfu.itis.mustafin.services.UserService;
import ru.kpfu.itis.mustafin.util.validation.LoginValidator;

import java.util.ArrayList;
import java.util.List;

@Component
public class Authenticator implements AuthenticationManager {

    @Autowired
    LoginValidator validator;

    @Autowired
    UserService service;

    private final List<GrantedAuthority> AUTHORITIES = new ArrayList<GrantedAuthority>();

    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String login = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();

        User currentUser = service.getByLogin(login);
        validator.validate(currentUser, login, password);
        GrantedAuthority grantedAuthority = new GrantedAuthorityImpl(currentUser.getRole());
        AUTHORITIES.clear();
        AUTHORITIES.add(grantedAuthority);

        return new UsernamePasswordAuthenticationToken(currentUser, null, AUTHORITIES);
    }
}
