package lotto.controller;

import lotto.constant.WinningTier;
import lotto.domain.Lotto;
import lotto.domain.Scoreboard;
import lotto.domain.Vendor;
import lotto.domain.WinningLotto;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

public class LottoController {

    private final InputView inputView;
    private final OutputView outputView;

    public LottoController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        int purchaseAmount = inputView.readPurchaseAmount();
        Vendor vendor = new Vendor(purchaseAmount);
        List<Lotto> lottos = vendor.issueLottos();
        outputView.printLottos(lottos);

        Lotto winningNumbers = inputView.readWinningNumbers();
        int bonusNumber = inputView.readBonusNumber();
        WinningLotto winningLotto = new WinningLotto(winningNumbers, bonusNumber);

        Scoreboard scoreboard = new Scoreboard();
        List<WinningTier> winningTiers = scoreboard.findWinningTiers(lottos, winningLotto);

        double profit = vendor.calculateProfit(winningTiers);
        outputView.printResults(winningTiers, profit);
    }
}
