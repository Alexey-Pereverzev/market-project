package org.example.november_market_2.api;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Модель пользователя")
public class UserDto {

    @Schema(description = "ID пользователя", required = true, example = "3")
    private Long id;

    @Schema(description = "Имя пользователя", required = true, maxLength = 36, minLength = 2, example = "admin")
    private String username;

    @Schema(description = "Email пользователя", required = true, maxLength = 50, minLength = 4, example = "admin@website.com")
    private String email;

    public UserDto() {
    }

    public UserDto(Long id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
