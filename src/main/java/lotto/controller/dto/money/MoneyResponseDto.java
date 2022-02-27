package lotto.controller.dto.money;

import lotto.domain.Money;

public class MoneyResponseDto {

    private final int price;
    private final int totalCount;

    private MoneyResponseDto(Money money) {
        this.price = money.getPrice();
        this.totalCount = money.calculateTicketCount();
    }

    public static MoneyResponseDto from(Money money) {
        return new MoneyResponseDto(money);
    }

    public int getPrice() {
        return price;
    }

    public int getTotalCount() {
        return totalCount;
    }
}
