package domain;

public class LottoTicketCount {
    private static final int LOTTO_TICKET_PRICE = 1_000;

    private final ManualCount manualCount;
    private final AutoCount autoCount;

    public LottoTicketCount(Money money, String inputCount) {
        this.manualCount = new ManualCount(inputCount);
        validateMoneyRange(money, manualCount.getManualCount());
        this.autoCount = new AutoCount(calculateAutoCount(money));
    }

    private void validateMoneyRange(Money money, int manualBuyCount) {
        if (money.getMoney() < manualBuyCount * LOTTO_TICKET_PRICE) {
            throw new IllegalArgumentException("구매하려고 하는 수동 티켓이 구입 금액보다 많습니다!");
        }
    }

    private int calculateAutoCount(Money money) {
        return (money.getMoney() - (manualCount.getManualCount() * LOTTO_TICKET_PRICE)) / LOTTO_TICKET_PRICE;
    }

    public ManualCount getManualCount() {
        return manualCount;
    }

    public AutoCount getAutoCount() {
        return autoCount;
    }
}