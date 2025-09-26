package com.klein.pokedextracker.auth.service;

import com.klein.pokedextracker.auth.model.PasswordId;
import com.klein.pokedextracker.auth.model.PasswordModel;
import com.klein.pokedextracker.auth.repository.PasswordRepository;
import com.klein.pokedextracker.core.exception.EntityNotFoundException;
import com.klein.pokedextracker.i18n.service.Ii18nService;
import com.klein.pokedextracker.user.model.UserModel;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class PasswordService {
    private final PasswordRepository passwordRepo;
    private final PasswordEncoder passwordEncoder;
    private final Ii18nService i18nService;

    public PasswordModel getActivePasswordForUser(String userId) throws EntityNotFoundException {
        return passwordRepo.findActivePasswordForUser(userId).orElseThrow(
                () -> new EntityNotFoundException(i18nService.getString("auth.error.passwordNotFound.message"))
        );
    }

    public PasswordModel createPassword(String password, UserModel userModel) {
        String encodedPassword = passwordEncoder.encode(password);
        Integer nextSequence = getNextPasswordSequenceForId(userModel.getUserId());

        PasswordModel passwordModel = new PasswordModel();
        passwordModel.setId(new PasswordId(userModel.getUserId(), nextSequence));
        passwordModel.setCreatedDate(LocalDate.now());
        passwordModel.setEncodedPassword(encodedPassword);

        passwordRepo.save(passwordModel);

        return passwordModel;
    };

    private Integer getNextPasswordSequenceForId(String userId) {
        Integer sequence = passwordRepo.findMaxSequenceByUserId(userId).orElse(null);
        return sequence != null ? ++sequence : 1;
    }
}
