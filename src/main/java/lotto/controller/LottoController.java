package lotto.controller;

import lotto.domain.Ball;
import lotto.domain.Lotto;
import lotto.domain.LottoResult;
import lotto.domain.Lottos;
import lotto.domain.Profit;
import lotto.domain.PurchaseAmount;
import lotto.domain.WinningLotto;
import lotto.view.Input;
import lotto.view.Output;

public class LottoController {
    private PurchaseAmount purchaseAmount;

    public void run() {
        Lottos lottos = buyLotto();
        WinningLotto winningLotto = pickLastWeekWinningNumbers();
        calculateLottoResult(lottos, winningLotto);
    }

    private Lottos buyLotto() {
        Output.askPurchaseAmount();
        try {
            purchaseAmount = new PurchaseAmount(Input.purchaseAmount());
            Lottos lottos = new Lottos(purchaseAmount);
            Output.lottos(lottos);
            return lottos;
        } catch (IllegalArgumentException error) {
            Output.error(error.getMessage());
            return buyLotto();
        }
    }

    private WinningLotto pickLastWeekWinningNumbers() {
        Lotto lotto = pickLotto();
        return combineBonusBall(lotto);
    }

    private Lotto pickLotto() {
        Output.askWinNumber();
        try {
            return new Lotto(Input.winNumber());
        } catch (IllegalArgumentException error) {
            Output.error(error.getMessage());
            return pickLotto();
        }
    }

    private WinningLotto combineBonusBall(Lotto lotto) {
        Output.askBonusBall();
        try {
            Ball bonusBall = new Ball(Input.bonusBall());
            return new WinningLotto(lotto, bonusBall);
        } catch (IllegalArgumentException error) {
            Output.error(error.getMessage());
            return combineBonusBall(lotto);
        }
    }

    private void calculateLottoResult(Lottos lottos, WinningLotto winningLotto) {
        Output.statisticsTitle();

        LottoResult lottoResult = new LottoResult();
        lottoResult.addMatchingCount(lottos, winningLotto);
        Output.lottoResult(lottoResult);

        Profit profit = new Profit();
        double profitRate = profit.calculate(lottoResult.getTotalMoney(), purchaseAmount);
        Output.profitRate(profitRate);
    }
}
