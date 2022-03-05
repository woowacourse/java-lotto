package lotto;

import java.util.List;
import lotto.domain.LottoBuyCount;
import lotto.domain.LottoMachine;
import lotto.domain.LottoResult;
import lotto.domain.Lottos;
import lotto.domain.LottosDto;
import lotto.domain.Money;
import lotto.domain.WinningNumber;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Application {
    public static void main(String[] args) {
        final Money money = createMoney();
        final LottoBuyCount lottoBuyCount = createCount(money);
        OutputView.printCount(lottoBuyCount);

        final Lottos lottos = createLottos(lottoBuyCount);
        OutputView.printLotto(LottosDto.from(lottos));

        final WinningNumber winningNumber = createWinningNumber();
        final LottoResult lottoResult = lottos.createLottoResult(winningNumber);
        OutputView.printResult(money, lottoResult);
        OutputView.printYield(lottoResult.calculateYield(money));
    }

    private static Money createMoney() {
        try {
            return LottoMachine.initMoney(InputView.askMoney());
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e);
            return createMoney();
        }
    }

    private static LottoBuyCount createCount(Money money) {
        try {
            return money.calculateCount(InputView.askManualCount());
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e);
            return createCount(money);
        }
    }

    private static Lottos createLottos(LottoBuyCount lottoBuyCount) {
        try {
            return LottoMachine.initLottos(InputView.askManualNumbers(lottoBuyCount.getManualCount()), lottoBuyCount);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e);
            return createLottos(lottoBuyCount);
        }
    }

    private static WinningNumber createWinningNumber() {
        try {
            return LottoMachine.initWinningNumber(InputView.askWinningNumbers(), InputView.askBonusNumber());
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e);
            return createWinningNumber();
        }
    }
}
