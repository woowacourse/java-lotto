package lotto;

import java.util.List;
import lotto.domain.BonusNumber;
import lotto.domain.Count;
import lotto.domain.Lotto;
import lotto.domain.Lottos;
import lotto.domain.LottosDto;
import lotto.domain.Money;
import lotto.domain.WinningNumber;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Application {
    public static void main(String[] args) {
        final Money money = initMoney();
        final Count count = initCount(money);
        OutputView.printCount(count);

        final Lottos lottos = buyLottos(count);
        OutputView.printLotto(LottosDto.from(lottos));

        final WinningNumber winningNumber = initWinningNumber();
        OutputView.printResult(money, lottos.createLottoResult(winningNumber));
    }

    private static Lottos buyLottos(Count count) {
        try {
            List<List<Integer>> manualNumbers = InputView.askManualNumbers(count.getManualCount());
            return new Lottos(manualNumbers, count.getAutoCount());
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e);
            return buyLottos(count);
        }
    }

    private static Money initMoney() {
        try {
            return new Money(InputView.askMoney());
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e);
            return initMoney();
        }
    }

    private static Count initCount(Money money) {
        try {
            return money.calculateCount(InputView.askManualCount());
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e);
            return initCount(money);
        }
    }

    private static WinningNumber initWinningNumber() {
        try {
            Lotto winningNumbers = new Lotto(InputView.askWinningNumbers());
            BonusNumber bonusNumber = new BonusNumber(InputView.askBonusNumber());
            return new WinningNumber(winningNumbers, bonusNumber);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e);
            return initWinningNumber();
        }
    }
}
