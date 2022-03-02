package lotto.controller;

import lotto.domain.factory.MoneyFactory;
import lotto.domain.lotto.Lotto;
import lotto.domain.factory.LottoFactory;
import lotto.domain.factory.NumberFactory;
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

        int manualLottoCount = requestCountManualLottoToBuy(money);
        Lottos lottos = new Lottos(money);

        requestManualLottoNumbers(manualLottoCount, lottos);

        OutputView.printLottoCount(manualLottoCount, lottos.getCount() - manualLottoCount);
        OutputView.printLottos(lottos);

        WinningLotto winningLotto = requestWinningLotto();

        Result result = new Result(lottos, winningLotto);
        OutputView.printResult(result);
        OutputView.printRateOfProfit(money.getRateOfProfit(result.getTotalProfit()));
    }

    private Money requestMoney() {
        try {
            String input = InputView.inputMoney();
            return MoneyFactory.valueOf(input);
        } catch (IllegalArgumentException exception) {
            OutputView.printException(exception);
            return requestMoney();
        }
    }

    private int requestCountManualLottoToBuy(Money money) {
        try {
            String input = InputView.inputCountManualLotto();
            int count = Integer.parseInt(input);
            money.pay(Lotto.PRICE, count);
            return count;
        } catch (IllegalArgumentException exception) {
            OutputView.printException(exception);
            return requestCountManualLottoToBuy(money);
        }
    }

    private void requestManualLottoNumbers(int manualLottoCount, Lottos lottos) {
        OutputView.printRequestManualLottoNumberUI();
        while (manualLottoCount-- > 0) {
            Lotto lotto = requestManualLottoNumber();
            lottos.add(lotto);
        }
    }

    private Lotto requestManualLottoNumber() {
        try {
            String text = InputView.inputManualLottoNumber();
            return LottoFactory.valueOf(text);
        } catch (IllegalArgumentException exception) {
            OutputView.printException(exception);
            return requestManualLottoNumber();
        }
    }

    private WinningLotto requestWinningLotto() {
        Lotto lotto = requestLotto();
        return requestWinningLottoContainingBonusNumber(lotto);
    }

    private Lotto requestLotto() {
        try {
            String input = InputView.inputLastWeekWinningNumbers();
            return LottoFactory.valueOf(input);
        } catch (IllegalArgumentException exception) {
            OutputView.printException(exception);
            return requestLotto();
        }
    }

    private WinningLotto requestWinningLottoContainingBonusNumber(Lotto lotto) {
        try {
            String input = InputView.inputBonusNumber();
            Number bonusNumber = NumberFactory.valueOf(input);
            return new WinningLotto(lotto, bonusNumber);
        } catch (IllegalArgumentException exception) {
            OutputView.printException(exception);
            return requestWinningLottoContainingBonusNumber(lotto);
        }
    }
}
