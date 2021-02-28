package lotto.domain;

public class Ticket {

    public static final int TICKET_PRICE = 1000;
    public static final String TICKET_MINIMUM_PRICE_ERROR_MESSAGE = "돈은 %d원 이상이어야 합니다.";
    public static final String MANUAL_BUY_ERROR_MESSAGE = "수동 구매는 %d장보다 더 많이 살 수 없습니다.";
    private final int totalCount;
    private int manualCount;

    public Ticket(final Money money) {
        validateMinimumTicketPrice(money);
        this.totalCount = money.getValue() / TICKET_PRICE;
    }

    public Ticket(final Money money, final int manualCount) {
        this(money);
        validateMaximumManualBuy(manualCount);
        this.manualCount = manualCount;
    }

    private void validateMinimumTicketPrice(final Money money) {
        if (money.getValue() < TICKET_PRICE) {
            throw new IllegalArgumentException(
                String.format(TICKET_MINIMUM_PRICE_ERROR_MESSAGE, TICKET_PRICE));
        }
    }

    private void validateMaximumManualBuy(final int manualCount) {
        if (manualCount > totalCount) {
            throw new IllegalArgumentException(
                String.format(MANUAL_BUY_ERROR_MESSAGE, totalCount));
        }
    }

    public int getTotalCount() {
        return totalCount;
    }

    public int getManualCount() {
        return manualCount;
    }

    public int getAutoCount() {
        return totalCount - manualCount;
    }

}
