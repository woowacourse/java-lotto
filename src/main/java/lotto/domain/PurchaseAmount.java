package lotto.domain;

public class PurchaseAmount {
    private int money;

    PurchaseAmount(int money) {
        validatePurchaseAmount(money);
        this.money = money;
    }

    private void validatePurchaseAmount(int money) {
        if (money <= LottoTicket.PRICE) {
            throw new PurchaseAmountException("최소 구입 금액은 " + LottoTicket.PRICE + "입니다.");
        }
    }

    void buy(int cost) {
        if (cost > money) {
            throw new PurchaseAmountException("금액이 부족합니다.");
        }
        money -= cost;
    }

    int available() {
        return money;
    }

    @Override
    public String toString() {
        return "잔액 : " + money;
    }
}
