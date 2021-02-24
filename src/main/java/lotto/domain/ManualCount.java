package lotto.domain;

import lotto.domain.money.Money;

public class ManualCount {

    public static final String INVALID_COUNT_ERROR_MESSAGE = "수동 구매 개수는 0미만이거나 구매가능 개수인 %d개를 초과할 수 없습니다.";

    private int value;

    public ManualCount(int value, Money money) {
        validateManualCount(value, money);
        this.value = value;
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
