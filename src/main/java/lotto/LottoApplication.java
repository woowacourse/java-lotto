package lotto;

import lotto.domain.*;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoApplication {
    public static void main(String[] args) {
        PurchasePrice purchasePrice = new PurchasePrice(InputView.requestPurchasePriceInput());
        int lottoCount = purchasePrice.calculateLottoCount();
        OutputView.printLottoCount(lottoCount);
        Lottos lottos = new Lottos(LottoGenerator.generate(lottoCount));
        OutputView.printLottos(lottos);

        WinningNumbers winningNumbers = new WinningNumbers(InputView.requestWinningNumbersInput());
        BonusNumber bonusNumber = new BonusNumber(InputView.requestBonusNumberInput());

        LottoResults lottoResults = new LottoResults(lottos.createMatchResults(winningNumbers, bonusNumber));
        OutputView.printLottoResult(lottoResults, purchasePrice);
    }
}
