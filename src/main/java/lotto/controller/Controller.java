package lotto.controller;

import java.util.Scanner;
import lotto.domain.BonusNumber;
import lotto.domain.Lottos;
import lotto.domain.Money;
import lotto.domain.ProfitRate;
import lotto.domain.RankCount;
import lotto.domain.WinningLotto;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Controller {

    public void run(Scanner scanner) {
        Money money = getMoney(scanner);
        Lottos lottos = Lottos.buyLottosByAuto(getMoney(scanner));
        OutputView.printLottos(lottos);
        OutputView.printNewLine();
        WinningLotto winningLotto = getWinningLotto(scanner);
        BonusNumber bonusNumber = getBonusNumber(scanner, winningLotto);
        OutputView.printNewLine();
        RankCount rankCount = new RankCount(lottos, winningLotto, bonusNumber);
        ProfitRate profitRate = new ProfitRate(rankCount.getTotalPrize(), money);
        OutputView.printWinningStatistic(rankCount, profitRate);
    }

    private Money getMoney(Scanner scanner) {
        try {
            return new Money(InputView.inputMoney(scanner));
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception.getMessage());
            return getMoney(scanner);
        }
    }

    private WinningLotto getWinningLotto(Scanner scanner) {
        try {
            return new WinningLotto(InputView.inputWinningLotto(scanner));
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception.getMessage());
            return getWinningLotto(scanner);
        }
    }

    private BonusNumber getBonusNumber(Scanner scanner, WinningLotto winningLotto) {
        try {
            return new BonusNumber(InputView.inputBonusNumber(scanner),winningLotto);
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception.getMessage());
            return getBonusNumber(scanner, winningLotto);
        }
    }
}
