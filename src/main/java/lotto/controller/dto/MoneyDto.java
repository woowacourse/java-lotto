package lotto.controller.dto;

import lotto.domain.Money;

public class MoneyDto {

    private final int price;
    private final int totalCount;

    private MoneyDto(Money money) {
        this.price = money.getPrice();
        this.totalCount = money.calculateTicketCount();
    }

    public static MoneyDto from(Money money) {
        return new MoneyDto(money);
    }

    public int getTotalCount() {
        return totalCount;
    }
}
