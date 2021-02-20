package lottogame.domain;

import lottogame.utils.CannotBuyLottoException;

public class LottoMachine {
    private static final int LOTTO_PRICE = 1000;

    public int purchaseQuantity(Money money) {
        int quantity = money.buyLotto(LOTTO_PRICE);
        if (quantity == 0) {
            throw new CannotBuyLottoException();
        }
        return quantity;
    }
}
