package lotto.controller;

import lotto.domain.*;
import lotto.domain.strategy.AutomaticCreationStrategy;
import lotto.domain.strategy.LottoCreationStrategy;
import lotto.domain.strategy.ManualCreationStrategy;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    public void run() {
        PurchasePrice purchasePrice = new PurchasePrice(InputView.requestPurchasePrice());
        LottoCount lottoCount =
                new LottoCount(purchasePrice.calculateTotalLottoCount(), InputView.requestNumberOfManualLotto());
        Lottos lottos = createManualLottos(lottoCount);
        lottos.concat(createAutomaticLottos(lottoCount));

        OutputView.printLottosInformation(lottoCount, lottos);

        WinningLotto winningLotto =
                new WinningLotto(InputView.requestWinningNumbers(), InputView.requestBonusNumber());

        Results results = Results.createMatchResults(lottos, winningLotto);
        OutputView.printLottoResult(results, purchasePrice);
    }

    private Lottos createManualLottos(LottoCount lottoCount) {
        OutputView.requestManualLottoMessage();
        LottoCreationStrategy manualCreationStrategy =
                new ManualCreationStrategy(InputView.requestManualLottos(lottoCount.getManualCount()));
        return manualCreationStrategy.create();
    }

    private Lottos createAutomaticLottos(LottoCount lottoCount) {
        LottoCreationStrategy automaticCreationStrategy =
                new AutomaticCreationStrategy(lottoCount.getAutomaticCount());
        return automaticCreationStrategy.create();
    }
}
