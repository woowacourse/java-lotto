package lotto;

import lotto.domain.BonusNumber;
import lotto.domain.Lottos;
import lotto.domain.PurchasePrice;
import lotto.domain.WinningNumbers;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoApplication {
    public static void main(String[] args) {
        String purchasePriceInput = InputView.requestPurchasePriceInput();
        PurchasePrice purchasePrice = new PurchasePrice(purchasePriceInput);

        int lottoCount = purchasePrice.calculateLottoCount();
        OutputView.printLottoCount(lottoCount);

        Lottos lottos = new Lottos(lottoCount);
        OutputView.printLottos(lottos);

        String winningNumbersInput = InputView.requestWinningNumbersInput();
        WinningNumbers winningNumbers = new WinningNumbers(winningNumbersInput);

        String bonusNumberInput = InputView.requestBonusNumberInput();
        BonusNumber bonusNumber = new BonusNumber(bonusNumberInput);
    }
}
