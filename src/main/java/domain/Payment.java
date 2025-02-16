package domain;

public class Payment {
    public static int UNIT = LottoTicket.LOTTO_PRICE;
    private final int money;

    public Payment(int money) {
        validatePositive(money);
        validateUnit(money);
        this.money = money;
    }

    private void validatePositive(int money) {
        if (money <= 0) {
            throw new IllegalArgumentException("구매금액이 양수가 아닙니다.");
        }
    }

    private void validateUnit(int money) {
        if (money % UNIT != 0) {
            throw new IllegalArgumentException("올바르지 않은 구매금액 단위입니다.");
        }
    }

    public int getMoney() {
        return money;
    }
}
