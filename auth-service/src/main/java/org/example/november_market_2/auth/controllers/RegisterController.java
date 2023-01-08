package org.example.november_market_2.auth.controllers;

import lombok.RequiredArgsConstructor;
import org.example.november_market_2.api.RegisterUserDto;
import org.example.november_market_2.api.StringResponse;
import org.example.november_market_2.auth.entities.User;
import org.example.november_market_2.auth.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reg")
public class RegisterController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<?> registerNewUser(@RequestBody RegisterUserDto registerUserDto) {

        Optional<User> user = userService.findByUsername(registerUserDto.getUsername());

        if (user.isPresent()) {
            return ResponseEntity.ok(new StringResponse("Такой пользователь уже есть в системе"));
        } else if (!registerUserDto.getPassword().equals(registerUserDto.getConfirmPassword())) {
            return ResponseEntity.ok(new StringResponse("Пароли не совпадают"));
        } else {
            String bcryptCachedPassword = passwordEncoder.encode(registerUserDto.getPassword());
            userService.createNewUser(registerUserDto, bcryptCachedPassword);
            return ResponseEntity.ok(new StringResponse("Пользователь с именем " + registerUserDto.getUsername()
                    + " успешно создан"));
        }
    }
}
