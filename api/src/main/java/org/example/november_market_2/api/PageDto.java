package org.example.november_market_2.api;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "Модель страницы товаров")
public class PageDto {
    @Schema(description = "Количество страниц в списке товаров", required = true, example = "4")
    private int totalPages;

    @Schema(description = "Номер отображаемой страницы", required = true, example = "2")
    private int number;

    @Schema(description = "Список товаров страницы", required = true)
    private List<ProductDto> content;

    public PageDto() {
    }

    public PageDto(int totalPages, int number, List<ProductDto> content) {
        this.totalPages = totalPages;
        this.number = number;
        this.content = content;
    }

    private PageDto(Builder builder) {
        setTotalPages(builder.totalPages);
        setNumber(builder.number);
        setContent(builder.content);
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public List<ProductDto> getContent() {
        return content;
    }

    public void setContent(List<ProductDto> content) {
        this.content = content;
    }


    public static final class Builder {
        private int totalPages;
        private int number;
        private List<ProductDto> content;

        private Builder() {
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public Builder withTotalPages(int val) {
            totalPages = val;
            return this;
        }

        public Builder withNumber(int val) {
            number = val;
            return this;
        }

        public Builder withContent(List<ProductDto> val) {
            content = val;
            return this;
        }

        public PageDto build() {
            return new PageDto(this);
        }
    }
}
