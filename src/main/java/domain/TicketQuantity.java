package domain;

import exception.InsufficientMoneyException;
import exception.NegativeTicketQuantityException;

public class TicketQuantity {
    private final int manualAmount;
    private final int autoAmount;

    public TicketQuantity(final LottoMoney lottoMoney, final int expectedManualValue) {
        validate(lottoMoney, expectedManualValue);
        this.manualAmount = expectedManualValue;
        this.autoAmount = lottoMoney.toTicketQuantity() - expectedManualValue;
    }

    private void validate(final LottoMoney lottoMoney, final int expectedManualValue) {
        validateRange(expectedManualValue);
        validateEnoughMoney(lottoMoney, expectedManualValue);
    }

    private void validateRange(final int expectedManualValue) {
        if (expectedManualValue < 0) {
            throw new NegativeTicketQuantityException(expectedManualValue);
        }
    }

    private void validateEnoughMoney(final LottoMoney lottoMoney, final int expectedManualValue) {
        if (!lottoMoney.isExchangeable(expectedManualValue)) {
            throw new InsufficientMoneyException();
        }
    }

    public int getManualAmount() {
        return manualAmount;
    }

    public int getAutoAmount() {
        return autoAmount;
    }
}
