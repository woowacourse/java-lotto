package Lotto.domain;

public class Money {
    private static final int MIN_MONEY_AMOUNT = 0;

    private final int money;

    public Money(String moneyInput) {
        validate(moneyInput);
        this.money = Integer.parseInt(moneyInput);
    }

    private void validate(String moneyInput) {
        try {
            Integer.parseInt(moneyInput);
        } catch (IllegalArgumentException e){
            throw new IllegalArgumentException("구입 금액은 정수입니다.");
        }

        if(isNegativeNumber(moneyInput)) {
            throw new IllegalArgumentException("구입 금액은 0보다 커야합니다.");
        }
    }

    private boolean isNegativeNumber(String moneyInput) {
        return Integer.parseInt(moneyInput) <= MIN_MONEY_AMOUNT;
    }
}
