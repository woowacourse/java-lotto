package lotto.domain.lottos.amount;

import lotto.domain.money.Money;

import java.util.Objects;

public class LottoAmount {

    public static final String MONEY_NULL_ERROR_MESSAGE = "money는 null이 될 수 없습니다.";
    public static final String MANUAL_AMOUNT_NULL_ERROR_MESSAGE = "manualAmount는 null이 될 수 없습니다.";
    public static final String INVALID_COUNT_ERROR_MESSAGE = "수동 구매 개수는 0미만이거나 구매가능 개수인 %d개를 초과할 수 없습니다.";
    public static final String NUMBER_FORMAT_ERROR_MESSAGE = "수동 구매 개수 입력은 숫자여야합니다.";

    private final int autoAmount;
    private final int manualAmount;

    public LottoAmount(final Money money, final String inputManualAmount) {
        Objects.requireNonNull(money, MONEY_NULL_ERROR_MESSAGE);
        Objects.requireNonNull(inputManualAmount, MANUAL_AMOUNT_NULL_ERROR_MESSAGE);
        int manualAmount = parseInt(inputManualAmount);
        validateManualCount(money, manualAmount);
        this.autoAmount = money.getLottoCount() - manualAmount;
        this.manualAmount = manualAmount;
    }

    private int parseInt(String inputManualAmount) {
        try {
            return Integer.parseInt(inputManualAmount);
        } catch (NumberFormatException e) {
            throw new NumberFormatException(NUMBER_FORMAT_ERROR_MESSAGE);
        }
    }

    private void validateManualCount(Money money, int manualAmount) {
        if (manualAmount < 0 || manualAmount > money.getLottoCount()) {
            throw new IllegalArgumentException(String.format(INVALID_COUNT_ERROR_MESSAGE, money.getLottoCount()));
        }
    }

    public int getAutoAmount() {
        return autoAmount;
    }

    public int getManualAmount() {
        return manualAmount;
    }
}
