package lotto.dto;

import lotto.domain.vo.Money;

public class RequestPurchaseMoneyDto {

    private final Money money;

    public RequestPurchaseMoneyDto(int money) {
        this.money = new Money(money);
    }

    public Money getMoney() {
        return money;
    }
}
