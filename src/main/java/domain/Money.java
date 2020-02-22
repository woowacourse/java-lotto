package domain;

public class Money {
    private static final int TICKET_PRICE = 1000;
    private static final int MIN_MONEY = 0;
    private static final int MAX_MONEY = 50000;

    private final long money;

    public Money(long money) {
        validateMoney(money);
        this.money = money;
    }

    private void validateMoney(long money) {
        validateMoneyRange(money);
        validateMoneyUnit(money);
    }

    public int getNumberOfTickets() {
        return (int) this.money / TICKET_PRICE;
    }

    /* 금액 제한 현실 반영 */
    private void validateMoneyRange(long money) {
        if (money < MIN_MONEY || money > MAX_MONEY) {
            throw new IllegalArgumentException("0원 이상, 5만원 이하 금액만 구매 가능합니다.");
        }
    }

    private void validateMoneyUnit(long money) {
        if (money % TICKET_PRICE != 0) {
            throw new IllegalArgumentException("천 원 단위로만 구매 가능합니다.");
        }
    }

    public long getMoney() {
        return this.money;
    }
}
