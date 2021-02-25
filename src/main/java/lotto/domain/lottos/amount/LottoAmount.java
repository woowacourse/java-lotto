package lotto.domain.lottos.amount;

import lotto.domain.money.Money;

import java.util.Objects;

public class LottoAmount {

    private static final String INVALID_AUTO_NUMBER_MESSAGE = "자동구매 개수는 0개 미만이 될 수 없습니다.";
    public static final String MONEY_NULL_ERROR_MESSAGE = "money는 null이 될 수 없습니다.";
    public static final String MANUAL_AMOUNT_NULL_ERROR_MESSAGE = "manualAmount는 null이 될 수 없습니다.";

    private final int autoAmount;
    private final int manualAmount;

    public LottoAmount(final Money money, final ManualAmount manualAmount) {
        validateLottoAmount(money, manualAmount);
        this.autoAmount = money.getLottoCount() - manualAmount.getValue();
        this.manualAmount = manualAmount.getValue();
    }

    private void validateLottoAmount(Money money, ManualAmount manualAmount) {
        Objects.requireNonNull(money, MONEY_NULL_ERROR_MESSAGE);
        Objects.requireNonNull(manualAmount, MANUAL_AMOUNT_NULL_ERROR_MESSAGE);
        if (money.getLottoCount() - manualAmount.getValue() < 0) {
            throw new IllegalArgumentException(INVALID_AUTO_NUMBER_MESSAGE);
        }
    }

    public int getAutoAmount() {
        return autoAmount;
    }

    public int getManualAmount() {
        return manualAmount;
    }
}
