package lotto.domain.ticket;

public class BettingInfo {
    private final BettingMoney bettingMoney;
    private final int manualAmount;

    public BettingInfo(BettingMoney bettingMoney, int manualAmount) {
        validateTicketCount(bettingMoney, manualAmount);
        this.bettingMoney = bettingMoney;
        this.manualAmount = manualAmount;
    }

    private void validateTicketCount(BettingMoney bettingMoney, int manualAmount) {
        int randomAmount = bettingMoney.getTicketCount() - manualAmount;
        if (randomAmount < 0) {
            throw new IllegalArgumentException(String.format("수동 로또 구매의 갯수(%d)가 입력 금액(%d)을 초과하였습니다.", manualAmount, bettingMoney.getMoney()));
        }
    }

    public int getRandomAmount() {
        return bettingMoney.getTicketCount() - manualAmount;
    }

    public int getManualAmount() {
        return manualAmount;
    }
}
