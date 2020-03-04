package lotto.domain;

public class LottoTicketCount {

    private static final int LOTTO_PRICE = 1000;
    private static final String NEGATIVE_NUMBER_ERROR_MESSAGE = "0 이상의 수만 입력 가능합니다.";
    private static final int MIN_COUNT = 0;
    private int manualCount;
    private int autoCount;

    public LottoTicketCount(int money, int manualCount) {
        validateCount(manualCount);
        this.manualCount = manualCount;
        this.autoCount = calculateTicketCount(money) - manualCount;
    }

    private void validateCount(int manualCount) {
        if (manualCount < MIN_COUNT) {
            throw new IllegalArgumentException(NEGATIVE_NUMBER_ERROR_MESSAGE);
        }
    }

    private int calculateTicketCount(int money) {
        return money / LOTTO_PRICE;
    }

    public int getAutoCount() {
        return autoCount;
    }

    public int getManualCount() {
        return manualCount;
    }
}
