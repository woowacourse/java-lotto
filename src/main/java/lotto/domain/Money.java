package lotto.domain;

public class Money {
    private final int buyPrice;

    public Money(final int buyPrice) {
        this.buyPrice = cuttingThousandPiece(buyPrice);
    }

    private int cuttingThousandPiece(int buyPrice) {
        return buyPrice - (buyPrice % BoughtLottos.BUY_PRICE);
    }

    public int getBuyPrice() {
        return buyPrice;
    }
}
