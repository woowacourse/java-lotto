package domain.dto;

import domain.Amount;

public record AmountDto(
        int amount
) {
    public AmountDto(Amount amount) {
        this(amount.getAmount());
    }
}
