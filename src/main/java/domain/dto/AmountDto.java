package domain.dto;

import domain.Money;

public record AmountDto(
        int amount
) {

    public AmountDto(Money money) {
        this(money.getMoney());
    }
}
