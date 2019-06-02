package lotto.domain;

import lotto.domain.exception.InvalidLottoPriceException;

public class LottoSeller {
    private static final int MIN_PRICE_OF_LOTTO = 1000;
    private static final int MIN_OF_CHANGE = 0;
    private static final String MIN_PURCHASE_ERROR_MESSAGE = "로또의 최소 구매 가격은 " + MIN_PRICE_OF_LOTTO + "원입니다.";
    private final Cash purchasePrice;

    public LottoSeller(Cash purchasePrice) {
        if (isDeficient(purchasePrice)) {
            throw new InvalidLottoPriceException(MIN_PURCHASE_ERROR_MESSAGE);
        }

        this.purchasePrice = purchasePrice;
    }

    private boolean isDeficient(Cash purchasePrice) {
        return purchasePrice.isLittleThan(MIN_PRICE_OF_LOTTO);
    }

    public int getNumOfLotto() {
        return purchasePrice.calculateQuotient(MIN_PRICE_OF_LOTTO);
    }

    public int getChange() {
        return purchasePrice.calculateRemainder(MIN_PRICE_OF_LOTTO);
    }

    public boolean hasChange() {
        return getChange() > MIN_OF_CHANGE;
    }
}
