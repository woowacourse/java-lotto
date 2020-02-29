package lotto.domain;

public class Money {
    private static final int ZERO = 0;
    private static final int LOTTO_UNIT = 1000;

    private int money;


    public Money(String money) {
        validateIntegerNumberFormat(money);
        validatePositiveNumber(money);
        validateLottoUnit(money);

        this.money = Integer.parseInt(money);
    }

    private static void validatePositiveNumber(String input) {
        if (Integer.parseInt(input) < ZERO) {
            throw new IllegalArgumentException("음수입니다. 재입력 해주세요");
        }
    }

    private static void validateIntegerNumberFormat(String input) {
        try {
            Integer.parseInt(input);
        } catch (RuntimeException e) {
            throw new NumberFormatException("정수만 입력 가능합니다. 재입력 해주세요.");
        }
    }

    private static void validateLottoUnit(String input) {
        if (Integer.parseInt(input) < LOTTO_UNIT) {
            throw new IllegalArgumentException("한장도 구매할수 없습니다. 재입력 해주세요");
        }
    }

    public int changeMoney() {
        return money % LOTTO_UNIT;
    }

    public String generateLottoTicketCount() {
        return String.valueOf(money / LOTTO_UNIT);
    }
}
