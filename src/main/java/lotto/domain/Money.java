package lotto.domain;

import lotto.util.Parser;

public class Money {

    private int money;
    private static final int THOUSAND = 1000;

    public Money(String money) {
        validate(money);
    }

    public double calculateProfit(int totalProfit) {
        return Math.floor((double) totalProfit /(money) * 100.0) / 100.0;
    }

    public int countsLotto() {
        return money / THOUSAND;
    }

    private void validate(String money) {
        int validatedMoney = Parser.validateNumber(money, "구입금액은 숫자여야 합니다.");
        validateUnit(validatedMoney);
        validateNegative(validatedMoney);
        validateLimit(validatedMoney);
        this.money = validatedMoney;
    }

    private void validateUnit(int validatedMoney) {
        if (validatedMoney % 1000 != 0) {
            throw new IllegalArgumentException("구입금액은 천원 단위여야 합니다.");
        }
    }
    private void validateNegative(int validatedMoney) {
        if (validatedMoney <= 0) {
            throw new IllegalArgumentException("구입금액은 천원부터입니다.");
        }
    }
    private void validateLimit(int validatedMoney) {
        if (validatedMoney > 100000) {
            throw new IllegalArgumentException("10만원까지 구매 가능합니다.");
        }
    }
}
