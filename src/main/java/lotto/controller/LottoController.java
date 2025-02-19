package lotto.controller;

import java.util.List;
import lotto.config.ApplicationConfiguration;
import lotto.domain.Lotto;
import lotto.domain.LottoMachine;
import lotto.domain.PurchaseAmount;
import lotto.domain.WinningLotto;
import lotto.domain.WinningTier;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    private final InputView inputView;
    private final OutputView outputView;
    private final LottoMachine lottoMachine;

    public LottoController(ApplicationConfiguration applicationConfiguration) {
        this.inputView = applicationConfiguration.getInputView();
        this.lottoMachine = applicationConfiguration.getLottoMachine();
        this.outputView = applicationConfiguration.getOutputView();
    }

    public void run() {
        PurchaseAmount purchaseAmount = readPurchaseAmount();
        List<Lotto> lottos = purchaseLottos(purchaseAmount);
        List<WinningTier> winningTiers = findWinningTiers(lottos);
        printWinningResult(winningTiers, purchaseAmount);
    }

    private PurchaseAmount readPurchaseAmount() {
        int amount = inputView.readPurchaseAmount();
        return new PurchaseAmount(amount);
    }

    private List<Lotto> purchaseLottos(PurchaseAmount purchaseAmount) {
        List<Lotto> lottos = lottoMachine.purchaseLotto(purchaseAmount);
        outputView.printLottos(lottos);
        return lottos;
    }

    private List<WinningTier> findWinningTiers(List<Lotto> lottos) {
        List<Integer> winningNumbers = inputView.readWinningNumbers();
        int bonusNumber = inputView.readBonusNumber();
        WinningLotto winningLotto = new WinningLotto(winningNumbers, bonusNumber);
        return lottoMachine.findWinningTiers(lottos, winningLotto);
    }

    private void printWinningResult(List<WinningTier> tiers, PurchaseAmount amount) {
        double profit = lottoMachine.calculateProfit(tiers, amount);
        outputView.printResults(tiers, profit);
    }
}
