package lotto.domain.purchaseamount;

import lotto.domain.lotto.LottoTicket;

public class PurchaseAmount {
    private int money;

    private PurchaseAmount(int money) {
        this.money = money;
        validatePurchaseAmount();
    }

    public static PurchaseAmount create(String moneyText) {
        try {
            return new PurchaseAmount(Integer.parseInt(moneyText.trim()));
        } catch (NumberFormatException e) {
            throw new PurchaseAmountException("금액은 숫자로 구성하세요.");
        }
    }

    private void validatePurchaseAmount() {
        if (money < MIN_MONEY) {
            throw new PurchaseAmountException("구입 금액은 양수로 설정 가능합니다.");
        }
    }

    public boolean buy(LottoTicket lottoTicket) {
        if (!canBuy(lottoTicket.getPrice())) {
            return false;
        }
        money -= lottoTicket.getPrice();
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
