package domain;

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
            throw new IllegalArgumentException("티켓의 수에는 음수가 올 수 없습니다: " + expectedManualValue);
        }
    }

    private void validateEnoughMoney(final LottoMoney lottoMoney, final int expectedManualValue) {
        if (!lottoMoney.isExchangeable(expectedManualValue)) {
            throw new IllegalArgumentException("티켓을 구매할 돈이 부족합니다");
        }
    }

    public int getManualAmount() {
        return manualAmount;
    }

    public int getAutoAmount() {
        return autoAmount;
    }
}
