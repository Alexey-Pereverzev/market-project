package org.example.november_market_2.api;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Модель запроса на регистрацию пользователя")
public class RegisterUserDto {

    @Schema(description = "Имя пользователя", required = true, maxLength = 36, minLength = 2, example = "bob")
    private String username;

    @Schema(description = "Пароль", required = true, maxLength = 80, minLength = 2, example = "some1password2")
    private String password;

    @Schema(description = "Повторный ввод пароля", required = true, maxLength = 80, minLength = 2, example = "some1password2")
    private String confirmPassword;

    @Schema(description = "Email пользователя", required = true, maxLength = 50, minLength = 4, example = "mail@gmail.com")
    private String email;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public RegisterUserDto() {
    }

    public RegisterUserDto(String username, String password, String confirmPassword, String email) {
        this.username = username;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.email = email;
    }

}
