package domain.dto;

import domain.Money;

public record AmountDto(
        int amount
) {

    public AmountDto(final Money money) {
        this(money.getMoney());
    }
}
