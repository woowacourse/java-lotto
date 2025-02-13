package lotto.controller;

import lotto.domain.Lotto;
import lotto.domain.Lottos;
import lotto.domain.Money;
import lotto.domain.Prizes;
import lotto.domain.LottoGenerator;
import lotto.domain.WinningLotto;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    private final InputView inputView;
    private final OutputView outputView;
    private Lottos lottos;
    private WinningLotto winningLotto;

    public LottoController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Money money = inputView.inputMoney();
        purchaseLotto(money);
        operateWinningLotto();
        operateStatistics(money);
        inputView.closeScanner();
    }

    private void purchaseLotto(Money money) {
        int lottoCounts = money.countsLotto();
        outputView.printCount(lottoCounts);
        lottos = new Lottos(lottoCounts, new LottoGenerator());
        outputView.printLottos(lottos);
    }

    private void operateWinningLotto() {
        Lotto winningLottoNumber = new Lotto(inputView.inputWinningLotto());
        String bonusNumber = inputView.inputBonusNumber();
        winningLotto = new WinningLotto(winningLottoNumber, bonusNumber);
    }

    private void operateStatistics(Money money) {
        Prizes prizes = lottos.calculatePrize(winningLotto);
        double totalProfit = prizes.calculateProfit(money);

        outputView.printResult(prizes.toString().trim());
        outputView.printProfitRate(totalProfit);
    }

}
