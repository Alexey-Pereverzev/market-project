package org.example.november_market_2.api;


import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Модель строкового ответа на запрос")
public class StringResponse {

    @Schema(description = "Ответ на запрос", required = true, example = "Заказ пользователя bob успешно создан")
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public StringResponse() {
    }

    public StringResponse(String value) {
        this.value = value;
    }
}



