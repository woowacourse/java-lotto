package domain;

import spark.utils.StringUtils;

public class Money {

    private int money;

    public Money(String input) {
        validateNullOrEmpty(input);
        int money = validateNumber(input);
        validateMoneyRange(money);
        this.money = money;
    }

    private void validateNullOrEmpty(String input) {
        if (StringUtils.isBlank(input)) {
            throw new IllegalArgumentException("input 값이 Null이거나 공백입니다.");
        }
    }

    private int validateNumber(String input) {
        int parseNumber;
        try {
            parseNumber = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("input 값이 숫자가 아닙니다.");
        }
        return parseNumber;
    }

    private void validateMoneyRange(int money) {
        if (money < 1000) {
            throw new IllegalArgumentException("input 값이 1000원보다 작습니다.");
        }
    }

    public int calculateLottoTicket() {
        return this.money / 1000;
    }
}
