package org.example.november_market_2.api;


import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Запрос на авторизацию")
public class JwtRequest {

    @Schema(description = "Имя пользователя", required = true, maxLength = 36, minLength = 2, example = "bob")
    private String username;

    @Schema(description = "Пароль", required = true, maxLength = 80, minLength = 2, example = "some1password2")
    private String password;

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

    public JwtRequest() {
    }

    public JwtRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
