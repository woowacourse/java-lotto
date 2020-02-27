package lotto;

import lotto.domain.*;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoApplication {
    public static void main(String[] args) {
        PurchasePrice purchasePrice = new PurchasePrice(InputView.requestPurchasePrice());
        int lottoCount = purchasePrice.calculateLottoCount();
        OutputView.printLottoCount(lottoCount);
        Lottos lottos = new Lottos(LottoGenerator.generate(lottoCount));
        OutputView.printLottos(lottos);

        WinningLotto winningLotto =
                new WinningLotto(InputView.requestWinningNumbers(), InputView.requestBonusNumber());

        Results results = new Results(lottos, winningLotto);
        OutputView.printLottoResult(results, purchasePrice);
    }
}
