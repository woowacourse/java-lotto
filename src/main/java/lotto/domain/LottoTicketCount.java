package lotto.domain;

public class LottoTicketCount {

    private static final int LOTTO_PRICE = 1000;
    private int manualCount;
    private int autoCount;

    public LottoTicketCount(int money, int manualCount) {
        this.manualCount = manualCount;
        this.autoCount = calculateTicketCount(money) - manualCount;
    }

    private int calculateTicketCount(int money){
        return money / LOTTO_PRICE;
    }

    public int getAutoCount() {
        return autoCount;
    }

    public int getManualCount() {
        return manualCount;
    }
}
