package lotto.controller;

import lotto.constant.WinningTier;
import lotto.domain.Lotto;
import lotto.domain.Vendor;
import lotto.domain.WinningLotto;
import lotto.service.LottoService;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

public class LottoController {

    private final InputView inputView;
    private final OutputView outputView;
    private final LottoService lottoService;

    public LottoController(InputView inputView, OutputView outputView, LottoService lottoService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoService = lottoService;
    }

    public void run() {
        int purchaseAmount = inputView.readPurchaseAmount();
        int lottoCount = lottoService.purchaseLotto(purchaseAmount);
        List<Lotto> lottos = lottoService.issueLottos(lottoCount);
        outputView.printLottos(lottos);

        Lotto winningNumbers = inputView.readWinningNumbers();
        int bonusNumber = inputView.readBonusNumber(winningNumbers);
        WinningLotto winningLotto = new WinningLotto(winningNumbers, bonusNumber);
        List<WinningTier> winningTiers = lottoService.findWinningTiers(lottos, winningLotto);

        Vendor vendor = new Vendor(purchaseAmount);
        double profit = vendor.calculateProfit(winningTiers);
        outputView.printResults(winningTiers, profit);
    }
}
