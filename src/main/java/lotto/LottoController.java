package lotto;

import lotto.domain.PurchaseAmount;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    public static void play() {
        PurchaseAmount purchaseAmount;

        try {
            OutputView.printStartGuide();
            purchaseAmount = new PurchaseAmount(InputView.InputPurchaseAmount());
            OutputView.printLottePieces(purchaseAmount.giveLottoPieces());
            OutputView.printChangeMoney(purchaseAmount.giveChangeMoney());
        } catch (RuntimeException e) {
            OutputView.printErrorMessage(e.getMessage());
            Application.main(null);
        }
    }
}
