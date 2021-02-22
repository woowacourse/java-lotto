package lotto.domain;

public class Money {

    private static final String MONEY_INT_ERROR = "[ERROR] 숫자만 입력할 수 있습니다";
    private static final String MONEY_RANGE_ERROR = "[ERROR] 금액을 1000원 이상 입력해주세요";
    private static final String MONEY_UNIT_ERROR = "[ERROR] 금액을 1000단위로 입력해주세요";
    private static final int THOUSAND = 1000;
    private final int money;

    public Money(String input) {
        int inputMoney = changeToInt(input);
        validateRange(inputMoney);
        validateUnit(inputMoney);
        this.money = inputMoney;
    }

    private int changeToInt(String input) {
        int money;
        try {
            money = Integer.parseInt(input);
        } catch (Exception e) {
            throw new IllegalArgumentException(MONEY_INT_ERROR);
        }
        return money;
    }

    private void validateRange(int money) {
        if (money < THOUSAND) {
            throw new IllegalArgumentException(MONEY_RANGE_ERROR);
        }
    }

    private void validateUnit(int money) {
        if (money % THOUSAND != 0) {
            throw new IllegalArgumentException(MONEY_UNIT_ERROR);
        }
    }

    public int count() {
        return money / THOUSAND;
    }

    public int getMoney() {
        return money;
    }
}