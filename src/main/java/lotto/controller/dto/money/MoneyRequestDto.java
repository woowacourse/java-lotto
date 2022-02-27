package lotto.controller.dto.money;

import lotto.domain.Money;

public class MoneyRequestDto {

    private final int price;

    private MoneyRequestDto(int price) {
        this.price = price;
    }

    public static MoneyRequestDto from(int price) {
        return new MoneyRequestDto(price);
    }

    public int getPrice() {
        return price;
    }
}
