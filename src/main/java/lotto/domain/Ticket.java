package lotto.domain;

public class Ticket {

    private static final int TICKET_PRICE = 1000;
    private final int count;
    private int manualCount;

    public Ticket(Money money) {
        validateMinimumTicketPrice(money);
        this.count = money.getValue() / TICKET_PRICE;
        this.manualCount = 0;
    }

    private void validateMinimumTicketPrice(final Money money) {
        if (money.getValue() < TICKET_PRICE) {
            throw new IllegalArgumentException(
                    String.format("돈은 %d원 이상이어야 합니다.", TICKET_PRICE));
        }
    }

    public void setManualCount(int manualCount) {
        if (manualCount > count) {
            throw new IllegalArgumentException("수동 로또 수가 전체 로또 수를 넘어갑니다.");
        }
        this.manualCount = manualCount;
    }

    public int getCount() {
        return count;
    }

    public int getManualCount() {
        return manualCount;
    }

    public int getRandomCount() {
        return count - manualCount;
    }

    public Money getPrice() {
        return new Money(TICKET_PRICE * count);
    }
}
