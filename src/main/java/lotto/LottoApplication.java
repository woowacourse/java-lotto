package lotto;

import lotto.domain.*;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;
import java.util.Map;

public class LottoApplication {
    public static void main(String[] args) {
        String purchasePriceInput = InputView.requestPurchasePriceInput();
        PurchasePrice purchasePrice = new PurchasePrice(purchasePriceInput);

        int lottoCount = purchasePrice.calculateLottoCount();
        OutputView.printLottoCount(lottoCount);

        List<Lotto> randomLottos = LottoGenerator.generate(lottoCount);
        Lottos lottos = new Lottos(randomLottos);
        OutputView.printLottos(lottos);

        String winningNumbersInput = InputView.requestWinningNumbersInput();
        WinningNumbers winningNumbers = new WinningNumbers(winningNumbersInput);

        String bonusNumberInput = InputView.requestBonusNumberInput();
        BonusNumber bonusNumber = new BonusNumber(bonusNumberInput);
    }
}
