package lotto.domain;

import lotto.domain.money.Money;

public class ManualAmount {

    public static final String INVALID_COUNT_ERROR_MESSAGE = "수동 구매 개수는 0미만이거나 구매가능 개수인 %d개를 초과할 수 없습니다.";
    public static final String NUMBER_FORMAT_ERROR_MESSAGE = "수동 구매 개수 입력은 숫자여야합니다.";

    private final int value;

    public ManualAmount(final String input,final Money money) {
        int value = parseInt(input);
        validateManualCount(value, money);
        this.value = value;
    }

    private int parseInt(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new NumberFormatException(NUMBER_FORMAT_ERROR_MESSAGE);
        }
    }

    private void validateManualCount(int value, Money money) {
        if (value < 0 || value > money.getLottoCount()) {
            throw new IllegalArgumentException(String.format(INVALID_COUNT_ERROR_MESSAGE, money.getLottoCount()));
        }
    }

    public int getValue() {
        return value;
    }
}
