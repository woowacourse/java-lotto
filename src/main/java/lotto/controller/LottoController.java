package lotto.controller;

import lotto.domain.Lotto;
import lotto.domain.Lottos;
import lotto.domain.Money;
import lotto.domain.Prizes;
import lotto.domain.RandomNumber;
import lotto.domain.WinningLotto;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    private final InputView inputView;
    private final OutputView outputView;

    public LottoController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Money money = new Money(inputView.inputMoney());
        Lottos lottos = purchaseLotto(money);

        outputView.printLottos(lottos);

        WinningLotto winningLotto = operateWinningLotto();
        operateStatistics(money, winningLotto, lottos);
        inputView.closeScanner();
    }

    private WinningLotto operateWinningLotto() {
        Lotto winningLottoNumber = new Lotto(inputView.inputWinningLotto());
        String bonusNumber = inputView.inputBonusNumber();
        return new WinningLotto(winningLottoNumber, bonusNumber);
    }

    private void operateStatistics(Money money, WinningLotto winningLotto, Lottos lottos) {
        Prizes prizes = winningLotto.calculatePrize(lottos);
        double totalProfit = prizes.calculateProfit(money);

        outputView.printResult(prizes);
        outputView.printProfitRate(totalProfit);
    }

    private Lottos purchaseLotto(Money money) {
        int lottoCounts = money.countsLotto();
        outputView.printCount(lottoCounts);
        return new Lottos(lottoCounts, new RandomNumber());
    }

}
