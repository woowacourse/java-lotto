package domain;

public class Money {
    private int money;

    public Money(int money) {
        if (money <= 0) {
            throw new IllegalArgumentException("금액은 0 이하로 입력될 수 없습니다.");
        }
        if (money % 1000 != 0) {
            throw new IllegalArgumentException("잔돈이 남습니다. 결제할 수 없습니다.");
        }
        this.money = money;
    }

    public int calculateEarnings(int earningMoney) {
        return 100 * earningMoney / money;
    }
}
