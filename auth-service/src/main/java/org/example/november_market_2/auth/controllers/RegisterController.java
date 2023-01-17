package org.example.november_market_2.auth.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.november_market_2.api.RegisterUserDto;
import org.example.november_market_2.api.StringResponse;
import org.example.november_market_2.auth.entities.User;
import org.example.november_market_2.auth.exceptions.AppError;
import org.example.november_market_2.auth.services.UserService;
import org.springframework.http.HttpStatus;
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
@Tag(name = "Регистрация", description = "Метод регистрации пользователя")
public class RegisterController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Operation(
            summary = "Запрос на регистрацию пользователя",
            responses = {
                    @ApiResponse(
                            description = "Заказ успешно создан", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = StringResponse.class))
                    ),
                    @ApiResponse(
                            description = "Ошибка ввода данных (пользователь уже есть в системе, не совпадают пароли)",
                            responseCode = "400",
                            content = @Content(schema = @Schema(implementation = AppError.class))
                    )
            }
    )
    @PostMapping("/register")
    public ResponseEntity<?> registerNewUser(@RequestBody RegisterUserDto registerUserDto) {

        Optional<User> user = userService.findByUsername(registerUserDto.getUsername());

        if (user.isPresent()) {
            return new ResponseEntity<>(new AppError("USER_ALREADY_EXISTS",
                    "Такой пользователь уже есть в системе"), HttpStatus.BAD_REQUEST);
        } else if (!registerUserDto.getPassword().equals(registerUserDto.getConfirmPassword())) {
            return new ResponseEntity<>(new AppError("NOT_MATCHING_PASSWORDS",
                    "Пароли не совпадают"), HttpStatus.BAD_REQUEST);
        } else {
            String bcryptCachedPassword = passwordEncoder.encode(registerUserDto.getPassword());
            userService.createNewUser(registerUserDto, bcryptCachedPassword);
            return ResponseEntity.ok(new StringResponse("Пользователь с именем " + registerUserDto.getUsername()
                    + " успешно создан"));
        }
    }
}
