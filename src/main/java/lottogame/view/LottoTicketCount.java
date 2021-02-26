package lottogame.view;

import lottogame.domain.Money;

public class LottoTicketCount {

    public static final int TICKET_PRICE = 1000;
    private int value;

    public LottoTicketCount(final String manualTicketCount, final Money money) {
        value = Integer.parseInt(manualTicketCount);
        positiveValidate(value);
        countValidate(value, money);
    }

    public void reduce() {
        value--;
    }

    public boolean isRemain() {
        return value > 0;
    }

    private void positiveValidate(final int value) {
        if (value < 0) {
            throw new IllegalArgumentException("시도 횟수는 음수가 될 수 없습니다.");
        }
    }

    private void countValidate(final int value, final Money money) {
        if (!money.isCanBuy(value * TICKET_PRICE)) {
            throw new IllegalArgumentException("보유 금액을 넘는 티켓은 구매할 수 없습니다.");
        }
    }
}
