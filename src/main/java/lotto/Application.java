package lotto;

import java.util.List;
import lotto.domain.LottoBuyCount;
import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.domain.Lottos;
import lotto.domain.LottosDto;
import lotto.domain.Money;
import lotto.domain.WinningNumber;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Application {
    public static void main(String[] args) {
        final Money money = initMoney();
        final LottoBuyCount lottoBuyCount = initCount(money);
        OutputView.printCount(lottoBuyCount);

        final Lottos lottos = initLottos(lottoBuyCount);
        OutputView.printLotto(LottosDto.from(lottos));

        final WinningNumber winningNumber = initWinningNumber();
        OutputView.printResult(money, lottos.createLottoResult(winningNumber));
    }

    private static Money initMoney() {
        try {
            return new Money(InputView.askMoney());
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e);
            return initMoney();
        }
    }

    private static LottoBuyCount initCount(Money money) {
        try {
            return money.calculateCount(InputView.askManualCount());
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e);
            return initCount(money);
        }
    }

    private static Lottos initLottos(LottoBuyCount lottoBuyCount) {
        try {
            List<List<Integer>> manualNumbers = InputView.askManualNumbers(lottoBuyCount.getManualCount());
            return new Lottos(manualNumbers, lottoBuyCount.getAutoCount());
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e);
            return initLottos(lottoBuyCount);
        }
    }

    private static WinningNumber initWinningNumber() {
        try {
            Lotto winningNumbers = new Lotto(InputView.askWinningNumbers());
            LottoNumber bonusNumber = new LottoNumber(InputView.askBonusNumber());
            return new WinningNumber(winningNumbers, bonusNumber);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e);
            return initWinningNumber();
        }
    }
}
