package lotto;

import lotto.domain.PurchasePrice;
import lotto.view.InputView;

public class LottoApplication {
    public static void main(String[] args) {
        String purchasePriceInput = InputView.requestPurchasePriceInput();

        PurchasePrice purchasePrice = new PurchasePrice(purchasePriceInput);

    }
}
