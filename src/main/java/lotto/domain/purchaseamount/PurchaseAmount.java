package lotto.domain.purchaseamount;

import lotto.domain.lotto.LottoTicket;

public class PurchaseAmount {
    private int money;

    private PurchaseAmount(int money) {
        this.money = money;
        validatePurchaseAmount();
    }

    private void validatePurchaseAmount() {
        if (money <= LottoTicket.PRICE) {
            throw new PurchaseAmountException("최소 구입 금액은 " + LottoTicket.PRICE + "입니다.");
        }
    }

    void buy(int cost) {
        if (cost > money) {
            throw new PurchaseAmountException("금액이 부족합니다.");
        }
    }

    public boolean buy(LottoTicket lottoTicket) {
        if (lottoTicket.PRICE > money) {
            return false;
        }
        money -= lottoTicket.PRICE;
        return true;
    }

    int available() {
        return money;
    }

    @Override
    public String toString() {
        return "잔액 : " + money;
    }
}
