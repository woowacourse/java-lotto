package lotto.domain;

import lotto.domain.exception.NotEnoughMoneyException;

public class Money {
    private final int buyPrice;

    public Money(final int buyPrice) {
        this.buyPrice = cuttingThousandPiece(buyPrice);
    }

    private int cuttingThousandPiece(int buyPrice) {
        if (buyPrice < BoughtLottos.BUY_PRICE) {
            throw new NotEnoughMoneyException("로또를 사기 위해서는 1000원 이상 필요합니다.");
        }

        return buyPrice - (buyPrice % BoughtLottos.BUY_PRICE);
    }

    public int getBuyPrice() {
        return buyPrice;
    }
}
