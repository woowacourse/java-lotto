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
        Lottos lottos = createManualLottos(lottoCount.getManualCount());
        lottos.concat(createAutomaticLottos(lottoCount.getAutomaticCount()));

        OutputView.printLottosInformation(lottoCount, lottos);

        WinningNumbers winningNumbers =
                new WinningNumbers(InputView.requestWinningLotto(), InputView.requestBonusNumber());

        Results results = Results.createMatchResults(lottos, winningNumbers);
        OutputView.printLottoResult(results, purchasePrice);
    }

    private Lottos createManualLottos(int count) {
        OutputView.requestManualLottoMessage();
        LottoCreationStrategy manualCreationStrategy =
                new ManualCreationStrategy(InputView.requestManualLottos(count));
        return manualCreationStrategy.create();
    }

    private Lottos createAutomaticLottos(int count) {
        LottoCreationStrategy automaticCreationStrategy =
                new AutomaticCreationStrategy(count);
        return automaticCreationStrategy.create();
    }
}
