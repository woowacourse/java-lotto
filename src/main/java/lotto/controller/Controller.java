package lotto.controller;

import lotto.domain.BonusNumber;
import lotto.domain.Lottos;
import lotto.domain.Money;
import lotto.domain.ProfitRate;
import lotto.domain.RankCount;
import lotto.domain.WinningLotto;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Controller {

    public void run() {
        Money money = getMoney();
        Lottos lottos = buyLottos(money);
        WinningLotto winningLotto = getWinningLotto();
        BonusNumber bonusNumber = getBonusNumber(winningLotto);
        getWinningStatistic(money, lottos, winningLotto, bonusNumber);
    }

    private Lottos buyLottos(Money money) {
        Lottos lottos = Lottos.buyLottosByAuto(money);
        OutputView.printLottos(lottos);
        OutputView.printNewLine();
        return lottos;
    }

    private void getWinningStatistic(Money money, Lottos lottos, WinningLotto winningLotto, BonusNumber bonusNumber) {
        OutputView.printNewLine();
        RankCount rankCount = new RankCount(lottos, winningLotto, bonusNumber);
        ProfitRate profitRate = new ProfitRate(rankCount.getTotalPrize(), money);
        OutputView.printWinningStatistic(rankCount, profitRate);
    }

    private Money getMoney() {
        try {
            return Money.generateMoneyByConsole(InputView.inputMoney());
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception);
            return getMoney();
        }
    }

    private WinningLotto getWinningLotto() {
        try {
            return WinningLotto.generateWinningLottoByConsole(InputView.inputWinningLotto());
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception);
            return getWinningLotto();
        }
    }

    private BonusNumber getBonusNumber(WinningLotto winningLotto) {
        try {
            return BonusNumber.generateBonusNumberByConsole(InputView.inputBonusNumber(), winningLotto);
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception);
            return getBonusNumber(winningLotto);
        }
    }
}
