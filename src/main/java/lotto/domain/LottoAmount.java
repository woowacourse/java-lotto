package lotto.domain;

import lotto.domain.money.Money;

public class LottoAmount {

    private static final String INVALID_AUTO_NUMBER_MESSAGE = "자동구매 개수는 0개 미만이 될 수 없습니다.";

    private int autoAmount;
    private int manualAmount;

    public LottoAmount(final Money money, final ManualAmount manualAmount) {
        this.autoAmount = money.getLottoCount() - manualAmount.getValue();
        this.manualAmount = manualAmount.getValue();
    }

    private void validateLottoAmount(Money money, ManualAmount manualAmount) {
        if (money.getLottoCount() - manualAmount.getValue() < 0) {
            throw new IllegalArgumentException(INVALID_AUTO_NUMBER_MESSAGE);
        }
    }
}
