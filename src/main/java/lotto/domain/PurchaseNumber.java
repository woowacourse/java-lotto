package lotto.domain;

public class PurchaseNumber {
    private static final int MINIMUM_MONEY = 1000;
    private static final int MINIMUM_NUMBER = 1;
    private static final String INVALID_PURCHASE_NUMBER_EXCEPTION_MESSAGE = "최소 구매 갯수 이하의 입력이 들어왔습니다.";
    private static final String INVALID_PURCHASE_MONEY_EXCEPTION_MESSAGE = "최소 구입 금액 이하의 입력이 들어왔습니다.";

    private final int purchaseNumber;

    private PurchaseNumber(int purchaseNumber) {
        validatePurchaseNumber(purchaseNumber);
        this.purchaseNumber = purchaseNumber;
    }

    public static PurchaseNumber calculate(int purchaseMoney) {
        validatePurchaseMoney(purchaseMoney);

        return new PurchaseNumber(purchaseMoney / MINIMUM_MONEY);
    }

    private static void validatePurchaseMoney(int purchaseMoney) {
        if (purchaseMoney < MINIMUM_MONEY) {
            throw new IllegalArgumentException(INVALID_PURCHASE_MONEY_EXCEPTION_MESSAGE);
        }
    }

    private void validatePurchaseNumber(int purchaseNumber) {
        if (purchaseNumber < MINIMUM_NUMBER) {
            throw new IllegalArgumentException(INVALID_PURCHASE_NUMBER_EXCEPTION_MESSAGE);
        }
    }

    public int getPurchaseNumber() {
        return purchaseNumber;
    }
}