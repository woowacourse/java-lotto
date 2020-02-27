package lotto.domain;

import lotto.domain.exception.OutOfRangeException;

public class PurchaseAmount {
    private static final int MINIMUM_AMOUNT = 1;
    private static final String OUT_OF_RANGE_PURCHASE_MONEY_EXCEPTION_MESSAGE = "Input money out of range (minimum : 1000).";
    private static final String OUT_OF_RANGE_LOTTO_AMOUNT_EXCEPTION_MESSAGE = "Lotto amount out of range (minimum : 0).";

    private final int randomLottoAmount;
    private final int manualLottoAmount;

    public PurchaseAmount(int randomLottoAmount, int manualLottoAmount) {
        validatePurchaseMoney(randomLottoAmount, manualLottoAmount);
        this.randomLottoAmount = randomLottoAmount;
        this.manualLottoAmount = manualLottoAmount;
    }

    private static void validatePurchaseMoney(int randomLottoAmount, int manualLottoAmount) {
        if (randomLottoAmount + manualLottoAmount < MINIMUM_AMOUNT) {
            throw new OutOfRangeException(OUT_OF_RANGE_PURCHASE_MONEY_EXCEPTION_MESSAGE);
        }
        if (randomLottoAmount < 0 || manualLottoAmount < 0) {
            throw new OutOfRangeException(OUT_OF_RANGE_LOTTO_AMOUNT_EXCEPTION_MESSAGE);
        }
    }

    public int getPurchaseNumber() {
        return randomLottoAmount + manualLottoAmount;
    }

    public int getRandomLottoAmount() {
        return randomLottoAmount;
    }

    public int getManualLottoAmount() {
        return manualLottoAmount;
    }
}