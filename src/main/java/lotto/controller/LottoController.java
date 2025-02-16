package lotto.controller;

import java.util.List;
import lotto.config.ApplicationConfiguration;
import lotto.domain.Lotto;
import lotto.domain.LottoMachine;
import lotto.domain.WinningLotto;
import lotto.domain.WinningTier;
import lotto.service.OutputService;
import lotto.view.InputView;

public class LottoController {

    private final InputView inputView;
    private final OutputService outputService;
    private final LottoMachine lottoMachine;

    public LottoController(ApplicationConfiguration applicationConfiguration) {
        this.inputView = applicationConfiguration.getInputView();
        this.lottoMachine = applicationConfiguration.getLottoMachine();
        this.outputService = applicationConfiguration.getOutputService();
    }

    public void run() {
        int purchaseAmount = readPurchaseAmount();
        List<Lotto> lottos = purchaseLottos(purchaseAmount);
        List<WinningTier> winningTiers = findWinningTiers(lottos);
        printWinningResult(winningTiers, purchaseAmount);
    }

    private int readPurchaseAmount() {
        return inputView.readPurchaseAmount();
    }

    private List<Lotto> purchaseLottos(int purchaseAmount) {
        List<Lotto> lottos = lottoMachine.purchaseLotto(purchaseAmount);
        outputService.printLottos(lottos);
        return lottos;
    }

    private List<WinningTier> findWinningTiers(List<Lotto> lottos) {
        List<Integer> winningNumbers = inputView.readWinningNumbers();
        int bonusNumber = inputView.readBonusNumber();
        WinningLotto winningLotto = new WinningLotto(new Lotto(winningNumbers), bonusNumber);
        return lottoMachine.findWinningTiers(lottos, winningLotto);
    }

    private void printWinningResult(List<WinningTier> tiers, int purchaseAmount) {
        double profit = lottoMachine.calculateProfit(tiers, purchaseAmount);
        outputService.printResults(tiers, profit);
    }
}
