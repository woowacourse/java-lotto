package lotto.controller;

import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.WinningLotto;
import lotto.domain.lotto.Lottos;
import lotto.domain.Money;
import lotto.domain.lotto.Number;
import lotto.domain.Result;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    public void play() {
        Money money = requestMoney();
        Lottos lottos = new Lottos(money);
        OutputView.printLottoCount(lottos.getCount());
        OutputView.printLottos(lottos);

        WinningLotto winningLotto = requestWinningLotto();

        Result result = new Result(lottos, winningLotto);
        OutputView.printResult(result);
        OutputView.printRateOfProfit(money.getRateOfProfit(result.getTotalProfit()));
    }

    private Money requestMoney() {
        try {
            String input = InputView.inputMoney();
            return new Money(input);
        } catch (IllegalArgumentException exception) {
            OutputView.printException(exception);
            return requestMoney();
        }
    }

    private WinningLotto requestWinningLotto() {
        Lotto lotto = requestLotto();
        return requestWinningLottoContainingBonusNumber(lotto);
    }

    private Lotto requestLotto() {
        try {
            String input = InputView.inputLastWeekWinningNumbers();
            return Lotto.of(input);
        } catch (IllegalArgumentException exception) {
            OutputView.printException(exception);
            return requestLotto();
        }
    }

    private WinningLotto requestWinningLottoContainingBonusNumber(Lotto lotto) {
        try {
            String input = InputView.inputBonusNumber();
            Number bonusNumber = new Number(input);
            return new WinningLotto(lotto, bonusNumber);
        } catch (IllegalArgumentException exception) {
            OutputView.printException(exception);
            return requestWinningLottoContainingBonusNumber(lotto);
        }
    }
}
