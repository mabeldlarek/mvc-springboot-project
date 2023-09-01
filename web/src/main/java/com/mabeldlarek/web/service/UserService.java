package com.mabeldlarek.web.service;

import com.mabeldlarek.web.dto.RegistrationDto;
import com.mabeldlarek.web.models.UserEntity;

public interface UserService {
    void saveUSer(RegistrationDto registrationDto);

    UserEntity findByEmail(String email);

    UserEntity findByUsername(String username);
}
