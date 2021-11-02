package com.app.burger.burgerapi.services;

import com.app.burger.burgerapi.repo.UserRepository;
import com.app.burger.burgerapi.repo.models.Role;
import com.app.burger.burgerapi.repo.models.User;
import com.app.burger.burgerapi.repo.models.UserDto;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void registerUser(UserDto dto) {
        if (userRepository.findAll().isEmpty()) {
            User user = new User();
            user.setUsername(dto.getUsername());
            user.setPassword(dto.getPassword());
            user.setRole(Role.ADMIN);

            userRepository.save(user);
            return;
        }

        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setRole(Role.USER);

        userRepository.save(user);
    }
}
