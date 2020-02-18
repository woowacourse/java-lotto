package lotto.view;

import lotto.domain.PurchaseNumber;

public class OutputView {
    private static final String PURCHASE_NUMBER_POSTFIX = "개를 구입했습니다.";

    public static void printPurchaseNumber(PurchaseNumber purchaseNumber) {
        System.out.println(purchaseNumber.getPurchaseNumber() + PURCHASE_NUMBER_POSTFIX);
    }
}
