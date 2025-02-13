package lotto.controller;

import java.util.List;
import lotto.config.ApplicationConfiguration;
import lotto.constant.WinningTier;
import lotto.domain.Lotto;
import lotto.domain.WinningLotto;
import lotto.service.InputService;
import lotto.service.LottoService;
import lotto.service.OutputService;

public class LottoController {

    private final InputService inputService;
    private final LottoService lottoService;
    private final OutputService outputService;

    public LottoController(ApplicationConfiguration applicationConfiguration) {
        this.inputService = applicationConfiguration.getInputService();
        this.lottoService = applicationConfiguration.getLottoService();
        this.outputService = applicationConfiguration.getOutputService();
    }

    public void run() {
        int purchaseAmount = inputService.readPurchaseAmount();
        int lottoCount = lottoService.purchaseLotto(purchaseAmount);
        List<Lotto> lottos = lottoService.issueLottos(lottoCount);
        outputService.printLottos(lottos);

        Lotto winningNumbers = inputService.readWinningNumbers();
        int bonusNumber = inputService.readBonusNumber(winningNumbers);
        WinningLotto winningLotto = new WinningLotto(winningNumbers, bonusNumber);
        List<WinningTier> winningTiers = lottoService.findWinningTiers(lottos, winningLotto);
        double profit = lottoService.calculateProfit(winningTiers, purchaseAmount);
        outputService.printResults(winningTiers, profit);
    }
}
