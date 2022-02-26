package lotto.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
        requestLastWeekWinningLotto();
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

    private void requestLastWeekWinningLotto() {
        Lotto winningLotto = requestWinningLotto();
        Number bonusNumber;
        do {
            String input = InputView.inputBonusNumber();
            bonusNumber = getValidNumber(input);
            lottoWinningNumber = getValidLottoWinningNumber(winningLotto, bonusNumber);
        } while (bonusNumber == null || lottoWinningNumber == null);
    }

    private Lotto requestWinningLotto() {
        Lotto winningLotto;
        do {
            String input = InputView.inputLastWeekWinningNumbers();
            winningLotto = toLotto(input.split(", "));
        } while (winningLotto == null);
        return winningLotto;
    }

    private Number getValidNumber(String input) {
        try {
            return new Number(input);
        } catch (IllegalArgumentException exception) {
            OutputView.printException(exception);
        }
        return null;
    }

    private LottoWinningNumber getValidLottoWinningNumber(Lotto winningLotto, Number bonusNumber) {
        try {
            return new LottoWinningNumber(winningLotto, bonusNumber);
        } catch (IllegalArgumentException exception) {
            OutputView.printException(exception);
        }
        return null;
    }

    private Lotto toLotto(String[] splitInput) {
        Lotto lotto = null;

        try {
            lotto = new Lotto(toList(splitInput));
        } catch (NumberFormatException exception) {
            System.out.println("숫자를 입력해주세요.");
        } catch (IllegalArgumentException exception) {
            OutputView.printException(exception);
        }

        return lotto;
    }

    private List<Integer> toList(String[] splitInput) {
        return Arrays.stream(splitInput)
            .map(Integer::parseInt)
            .collect(Collectors.toList());
    }

    private void printLottoResult() {
        Result result = lottos.getResult(lottoWinningNumber);
        OutputView.printResult(result);
        OutputView.printRateOfProfit(result.getRateOfProfit(money));
    }
}
