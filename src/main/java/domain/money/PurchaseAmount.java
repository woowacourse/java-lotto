package domain.money;

import domain.IssuedLotto;

public class PurchaseAmount extends Money {
    public static final int MINIMUM_AMOUNT = 1000;

    public PurchaseAmount(int amountOfMoney) {
        super(amountOfMoney);
    }

    public static PurchaseAmount valueOf(int amountOfMoney) {
        validatePurchaseAmount(amountOfMoney);
        validateIfMultipleOfPricePerLotto(amountOfMoney);

        return new PurchaseAmount(amountOfMoney);
    }

    private static void validatePurchaseAmount(int amountOfMoney) {
        if (amountOfMoney < MINIMUM_AMOUNT) {
            throw new IllegalPurchasementException();
        }
    }

    private static void validateIfMultipleOfPricePerLotto(int amountOfMoney) {
        if (amountOfMoney % IssuedLotto.PRICE != 0) {
            throw new IllegalPurchasementException();
        }
    }

    public void checkNumberOfManualIssue(int numberOfManualIssue) {
        if (numberOfManualIssue * IssuedLotto.PRICE > amountOfMoney) {
            throw new IllegalNumberOfManualIssueException();
        }
    }
}
