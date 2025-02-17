package lotto.controller;

import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.domain.Lottos;
import lotto.domain.Money;
import lotto.domain.Prizes;
import lotto.domain.WinningLotto;
import lotto.util.NumberGenerator;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    private final InputView inputView;
    private final OutputView outputView;
    private final NumberGenerator numberGenerator;

    public LottoController(final InputView inputView, final OutputView outputView, final NumberGenerator numberGenerator) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.numberGenerator = numberGenerator;
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
        LottoNumber bonusNumber = new LottoNumber(inputView.inputBonusNumber());
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
        return new Lottos(lottoCounts, numberGenerator);
    }

}
