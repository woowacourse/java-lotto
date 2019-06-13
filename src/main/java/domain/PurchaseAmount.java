package domain;

public class PurchaseAmount {
    private static final int FIT_AMOUNT = 0;

    private int amountOfMoney;

    private PurchaseAmount(int amountOfMoney) {
        this.amountOfMoney = amountOfMoney;
    }

    public static PurchaseAmount valueOf(int amountOfMoney) {
        validateAmountOfMoney(amountOfMoney);
        validateIfMultipleOfPricePerLotto(amountOfMoney);

        return new PurchaseAmount(amountOfMoney);
    }

    private static void validateAmountOfMoney(int amountOfMoney) {
        if (amountOfMoney < IssuedLotto.PRICE) {
            throw new IllegalArgumentException("고작 그정도 돈으로는 로또를 살 수 없습니다");
        }
    }

    private static void validateIfMultipleOfPricePerLotto(int amountOfMoney) {
        if (amountOfMoney % IssuedLotto.PRICE != FIT_AMOUNT) {
            throw new IllegalArgumentException(IssuedLotto.PRICE + "원의 배수만 입력할 수 있습니다.");
        }
    }

    public int getMoneyAmount() {
        return amountOfMoney;
    }

    public PurchaseAmount getChangeOf(PurchaseAmount purchaseAmount) {
        return new PurchaseAmount(this.amountOfMoney - purchaseAmount.getMoneyAmount());
    }

    public void checkNumberOfManualIssue(int numberOfManualIssue) {
        if (numberOfManualIssue * IssuedLotto.PRICE > amountOfMoney) {
            throw new IllegalNumberOfManualIssueException();
        }
    }
}
