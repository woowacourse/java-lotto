package lotto.controller;

import lotto.domain.*;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.stream.Collectors;

public class LottoController {
    public void run() {
        PurchasePrice purchasePrice = new PurchasePrice(InputView.requestPurchasePrice());
        LottoCount lottoCount = new LottoCount(purchasePrice.calculateTotalLottoCount(), InputView.requestNumberOfManualLotto());

        OutputView.requestManualLottoMessage();
        Lottos lottos = new Lottos();
        for (int i = 0; i < lottoCount.getManualCount(); i++) {
            lottos.add(new Lotto(InputView.requestManualLotto().stream()
                    .map(Ball::valueOf)
                    .collect(Collectors.toList())));
        }

        OutputView.printLottoCount(lottoCount);
        lottos.addAll(LottoGenerator.generate(lottoCount.getAutomaticCount()));
        OutputView.printLottos(lottos);

        WinningLotto winningLotto =
                new WinningLotto(InputView.requestWinningNumbers(), InputView.requestBonusNumber());

        Results results = Results.createMatchResults(lottos, winningLotto);
        OutputView.printLottoResult(results, purchasePrice);
    }
}
