package lotto.controller;

import java.util.ArrayList;
import java.util.List;
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
    private WinningLotto winningLotto;

    public LottoController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Money money = inputView.inputMoney();
        Lottos lottos = purchaseLotto(money);
        operateWinningLotto();
        operateStatistics(money, lottos);
        inputView.closeScanner();
    }

    private Lottos purchaseLotto(Money money) {
        int lottoCounts = money.countsLotto();
        outputView.printCount(lottoCounts);
        Lottos lottos = publishLottos(lottoCounts);
        outputView.printLottos(lottos);
        return lottos;
    }

    private Lottos publishLottos(int lottoCounts) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < lottoCounts; i++) {
            lottos.add(new Lotto(new LottoGenerator()));
        }
        return new Lottos(lottos);
    }

    private void operateWinningLotto() {
        Lotto winningLottoNumber = new Lotto(inputView.inputWinningLotto());
        String bonusNumber = inputView.inputBonusNumber();
        winningLotto = new WinningLotto(winningLottoNumber, bonusNumber);
    }

    private void operateStatistics(Money money, Lottos lottos) {
        Prizes prizes = lottos.calculatePrize(winningLotto);
        double totalProfit = prizes.calculateProfit(money);

        outputView.printResult(prizes.toString().trim());
        outputView.printProfitRate(totalProfit);
    }

}
