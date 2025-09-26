package com.klein.pokedextracker.auth.service;

import com.klein.pokedextracker.auth.model.GlobalRole;
import com.klein.pokedextracker.core.exception.EntityNotFoundException;
import com.klein.pokedextracker.i18n.service.Ii18nService;
import com.klein.pokedextracker.user.model.UserModel;
import com.klein.pokedextracker.user.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final IUserService userService;
    private final PasswordService passwordService;
    private final Ii18nService i18nService;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        UserModel userModel = userService.getUserByUserId(userId).orElseThrow(
                () -> new EntityNotFoundException(i18nService.getString("auth.error.userWithIdNotFound.message", userId)));
        return new User(userModel.getUserId(), passwordService.getActivePasswordForUser(userModel.getUserId())
                .getEncodedPassword(),
                List.of(new SimpleGrantedAuthority(GlobalRole.USER.toString())));
    }
}
