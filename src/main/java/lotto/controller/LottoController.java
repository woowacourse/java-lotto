package lotto.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.Lottos;
import lotto.domain.Money;
import lotto.domain.lotto.Number;
import lotto.domain.Result;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    private Money money;
    private Lottos lottos;
    private Lotto lastWeekWinningLotto;
    private Number bonusNumber;


    public void play() {
        requestMoney();
        buyLottos();
        requestLastWeekWinningLotto();
        checkTheLottoResult();
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

    private void buyLottos() {
        lottos = new Lottos(money);
        OutputView.printLottoCount(lottos.getCount());
        OutputView.printLottos(lottos);
    }

    private void requestLastWeekWinningLotto() {
        do {
            String input = InputView.inputLastWeekWinningNumbers();
            lastWeekWinningLotto = toLotto(input.split(", "));
            requestBonusNumber();
        } while (lastWeekWinningLotto == null);
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

    private void requestBonusNumber() {
        do {
            String input = InputView.inputBonusNumber();
            bonusNumber = toNumber(input, lastWeekWinningLotto);
        } while (bonusNumber == null);
    }

    private Number toNumber(String input, Lotto lotto) {
        try {
            Number number = new Number(input);
            validateDuplicate(lotto, number);
            return number;
        } catch (IllegalArgumentException exception) {
            OutputView.printException(exception);
        }

        return null;
    }

    private void validateDuplicate(Lotto lotto, Number number) {
        if (lotto.contains(number)) {
            throw new IllegalArgumentException("당첨 번호와 중복입니다.");
        }
    }

    private void checkTheLottoResult() {
        Result result = lottos.getResult(lastWeekWinningLotto, bonusNumber);
        OutputView.printResult(result);
        OutputView.printRateOfProfit(result.getRateOfProfit(money));
    }
}
