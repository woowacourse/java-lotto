package lotto.controller;

import lotto.domain.Lotto;
import lotto.domain.Lottos;
import lotto.domain.Prizes;
import lotto.domain.WinningLotto;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private Lottos lottos;
    private WinningLotto winningLotto;

    public void run() {
        int money = inputView.inputMoney();
        purchaseLotto(money);
        operateWinningLotto();
        operateStatistics(money);
        inputView.closeScanner();
    }

    private void operateWinningLotto() {
        Lotto winningLottoNumber = new Lotto(inputView.inputWinningLotto());
        int bonusNumber = inputView.inputBonusNumber();
        winningLotto = new WinningLotto(winningLottoNumber, bonusNumber);
    }

    private void operateStatistics(int money) {
        Prizes prizes = lottos.calculatePrize(winningLotto);
        double totalProfit = prizes.calculateProfit(money);

        outputView.printResult(prizes.toString().trim());
        outputView.printProfitRate(totalProfit);
    }

    private void purchaseLotto(int money) {
        int lottoCounts = money / 1000;
        outputView.printCount(lottoCounts);
        lottos = new Lottos(lottoCounts);
        outputView.printLottos(lottos);
    }

}
