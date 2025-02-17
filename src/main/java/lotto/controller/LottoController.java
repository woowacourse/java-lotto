package lotto.controller;

import lotto.constant.WinningTier;
import lotto.domain.Lotto;
import lotto.domain.Vendor;
import lotto.domain.WinningLotto;
import lotto.utility.RandomGenerator;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.ArrayList;
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
        Vendor vendor = new Vendor(new RandomGenerator(), purchaseAmount);
        List<Lotto> lottos = vendor.issueLottos();
        outputView.printLottos(lottos);

        WinningLotto winningLotto = this.getWinningLotto();
        List<WinningTier> winningTiers = this.getWinningTiers(lottos, winningLotto);

        double profit = vendor.calculateProfit(winningTiers);
        outputView.printResults(winningTiers, profit);
    }

    public WinningLotto getWinningLotto() {
        Lotto winningNumbers = inputView.readWinningNumbers();
        int bonusNumber = inputView.readBonusNumber();
        return new WinningLotto(winningNumbers, bonusNumber);
    }

    public List<WinningTier> getWinningTiers(List<Lotto> lottos, WinningLotto winningLotto) {
        List<WinningTier> winningTiers = new ArrayList<>();
        for (Lotto lotto : lottos) {
            WinningTier tier = winningLotto.findWinningTier(lotto);
            winningTiers.add(tier);
        }
        return winningTiers;
    }
}
