package lotto.domain.purchaseamount;

import lotto.domain.LottoQuantity;
import lotto.domain.lotto.LottoTicket;

public class PurchaseAmount {
    private int money;

    private PurchaseAmount(int money) {
        validatePurchaseAmount(money);
        this.money = money;
    }

    public static PurchaseAmount create(String moneyText) {
        try {
            return new PurchaseAmount(Integer.parseInt(moneyText.trim()));
        } catch (NumberFormatException e) {
            throw new PurchaseAmountException("구입 금액은 숫자로 구성하세요.");
        }
    }

    private void validatePurchaseAmount(int money) {
        if (money < LottoTicket.PRICE) {
            throw new PurchaseAmountException("로또 한 장은 " + LottoTicket.PRICE + "원 입니다.");
        }
    }

    public LottoQuantity maxLottoQuantity() {
        return LottoQuantity.create(money / LottoTicket.PRICE);
    }

    public boolean buy(int price) {
        if (!canBuy(price)) {
            return false;
        }
        money -= price;
        return true;
    }

    public boolean canBuy(int price) {
        return money >= price;
    }

    public int available() {
        return money;
    }

    @Override
    public String toString() {
        return "잔액 : " + money;
    }
}
