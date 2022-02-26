package lotto.controller;

import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.LottoWinningNumber;
import lotto.domain.lotto.Lottos;
import lotto.domain.Money;
import lotto.domain.lotto.Number;
import lotto.domain.Result;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    private Money money;
    private Lottos lottos;
    private LottoWinningNumber lottoWinningNumber;


    public void play() {
        requestMoney();
        printbuyLottos();
        requestBonusNumber(requestWinningLotto());
        printLottoResult();
    }

    private void requestMoney() {
        do {
            String input = InputView.inputMoney();
            money = toMoney(input);
        } while (money == null);
    }

    private Money toMoney(String input) {
        Money money = null;
        try {
            money = new Money(input);
        } catch (IllegalArgumentException exception) {
            OutputView.printException(exception);
        }
        return money;
    }

    private void printbuyLottos() {
        lottos = new Lottos(money);
        OutputView.printLottoCount(lottos.getCount());
        OutputView.printLottos(lottos);
    }

    private Lotto requestWinningLotto() {
        try {
            String input = InputView.inputLastWeekWinningNumbers();
            return Lotto.of(input);
        } catch (IllegalArgumentException exception) {
            OutputView.printException(exception);
            return requestWinningLotto();
        }
    }

    private void requestBonusNumber(Lotto winningLotto) {
        try {
            String input = InputView.inputBonusNumber();
            Number bonusNumber = new Number(input);
            lottoWinningNumber = new LottoWinningNumber(winningLotto, bonusNumber);
        } catch (IllegalArgumentException exception) {
            OutputView.printException(exception);
            requestBonusNumber(winningLotto);
        }
    }

    private void printLottoResult() {
        Result result = lottos.getResult(lottoWinningNumber);
        OutputView.printResult(result);
        OutputView.printRateOfProfit(result.getRateOfProfit(money));
    }
}
