package lotto;

import lotto.domain.PurchasePrice;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoApplication {
    public static void main(String[] args) {
        String purchasePriceInput = InputView.requestPurchasePriceInput();

        PurchasePrice purchasePrice = new PurchasePrice(purchasePriceInput);
        int lottoCount = purchasePrice.calculateLottoCount();

        OutputView.printLottoCount(lottoCount);

    }
}
