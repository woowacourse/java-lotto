package lotto.controller;

import lotto.constant.WinningTier;
import lotto.domain.Lotto;
import lotto.domain.Lottos;
import lotto.domain.Vendor;
import lotto.domain.WinningLotto;
import lotto.utility.LottoNumberGenerator;
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
        Vendor vendor = new Vendor(new LottoNumberGenerator(), purchaseAmount);
        Lottos lottos = vendor.issueLottos();
        outputView.printLottos(lottos);

        WinningLotto winningLotto = this.getWinningLotto();
        List<WinningTier> winningTiers = lottos.getWinningTiers(winningLotto);

        double profit = vendor.calculateProfit(winningTiers);
        outputView.printResults(winningTiers, profit);
    }

    public WinningLotto getWinningLotto() {
        Lotto winningNumbers = inputView.readWinningNumbers();
        int bonusNumber = inputView.readBonusNumber();
        return new WinningLotto(winningNumbers, bonusNumber);
    }
}
