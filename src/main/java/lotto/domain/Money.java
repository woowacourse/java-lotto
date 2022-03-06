package lotto.domain;

public class Money {
    private static final String NOT_INTEGER_ERROR_MESSAGE = "숫자가 아닙니다.";
    private static final String UNDER_MINIMUM_ERROR_MESSAGE = "1000원 미만의 금액은 입력할 수 없습니다.";
    private static final String NOT_QUANTIZED_ERROR_MESSAGE = "입력 금액은 1000원 단위여야 합니다.";
    private static final String NEGATIVE_INPUT_ERROR_MESSAGE = "구매 개수는 음수일 수 없습니다.";
    private static final String NOT_ENOUGH_MONEY_ERROR_MESSAGE = "소지한 금액이 부족합니다.";
    private static final int UNIT_PRICE = 1000;

    private final int initialMoney;
    private int money;

    public Money(String input) {
        isNumberFormat(input);
        int value = Integer.parseInt(input);
        isUnderMinimum(value);
        isDivideByUnitPrice(value);
        this.initialMoney = value;
        this.money = initialMoney;

    }

    public void validatePurchasableNumberInput(int manualPurchaseNumberInput) {
        if (manualPurchaseNumberInput < 0) {
            throw new IllegalArgumentException(NEGATIVE_INPUT_ERROR_MESSAGE);
        }
        if (manualPurchaseNumberInput > getPurchasableCount()) {
            throw new IllegalArgumentException(NOT_ENOUGH_MONEY_ERROR_MESSAGE);
        }
    }

    public int getPurchasableCount() {
        return money / UNIT_PRICE;
    }

    public void buyLotto(int purchasedLottoCount) {
        money -= purchasedLottoCount * UNIT_PRICE;
    }

    public int getLottoCount() {
        return money / UNIT_PRICE;
    }

    public int getInitialMoney() {
        return initialMoney;
    }

    private void isNumberFormat(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(NOT_INTEGER_ERROR_MESSAGE);
        }
    }

    private void isUnderMinimum(int value) {
        if (value < UNIT_PRICE) {
            throw new IllegalArgumentException(UNDER_MINIMUM_ERROR_MESSAGE);
        }
    }

    private void isDivideByUnitPrice(int value) {
        if (value % UNIT_PRICE != 0) {
            throw new IllegalArgumentException(NOT_QUANTIZED_ERROR_MESSAGE);
        }
    }
}
