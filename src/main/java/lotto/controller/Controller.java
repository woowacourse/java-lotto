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
        Lottos lottos = Lottos.buyLottosByAuto(money);
        OutputView.printLottos(lottos);
        OutputView.printNewLine();
        WinningLotto winningLotto = getWinningLotto();
        BonusNumber bonusNumber = getBonusNumber(winningLotto);
        OutputView.printNewLine();
        RankCount rankCount = new RankCount(lottos, winningLotto, bonusNumber);
        ProfitRate profitRate = new ProfitRate(rankCount.getTotalPrize(), money);
        OutputView.printWinningStatistic(rankCount, profitRate);
    }

    private Money getMoney() {
        try {
            return new Money(InputView.inputMoney());
        } catch (Exception exception) {
            OutputView.printErrorMessage(exception.getMessage());
            return getMoney();
        }
    }

    private WinningLotto getWinningLotto() {
        try {
            return new WinningLotto(InputView.inputWinningLotto());
        } catch (Exception exception) {
            OutputView.printErrorMessage(exception.getMessage());
            return getWinningLotto();
        }
    }

    private BonusNumber getBonusNumber(WinningLotto winningLotto) {
        try {
            return new BonusNumber(InputView.inputBonusNumber(), winningLotto);
        } catch (Exception exception) {
            OutputView.printErrorMessage(exception.getMessage());
            return getBonusNumber(winningLotto);
        }
    }
}
