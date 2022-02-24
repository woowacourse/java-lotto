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

    public void play() {
        Money money = getBuyMoney();
        Lottos lottos = buyLottos(money);
        Lotto lastWeekWinningLotto = getLastWeekWinningLotto();
        Number bonusNumber = getBonusNumber(lastWeekWinningLotto);
        Result result = getResult(lottos, lastWeekWinningLotto, bonusNumber);
        getRateofProfit(money, result);
    }

    private Money getBuyMoney() {
        Money money;

        do {
            String input = InputView.inputMoney();
            money = toMoney(input);
        } while (money == null);

        return money;
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

    private Lottos buyLottos(Money money) {
        Lottos lottos = new Lottos(money);
        OutputView.printLottoCount(lottos.getCount());
        OutputView.printLottos(lottos);
        return lottos;
    }

    private Lotto getLastWeekWinningLotto() {
        Lotto lotto;

        do {
            String input = InputView.inputLastWeekWinningNumbers();
            lotto = toLotto(input.split(", "));
        } while (lotto == null);

        return lotto;
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

    private Number getBonusNumber(Lotto lotto) {
        Number number;

        do {
            String input = InputView.inputBonusNumber();
            number = toNumber(input, lotto);
        } while (number == null);

        return number;
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

    private Result getResult(Lottos lottos, Lotto lastWeekWinningNumbers, Number bonusNumber) {
        Result result = lottos.getResult(lastWeekWinningNumbers, bonusNumber);
        OutputView.printResult(result);
        return result;
    }

    private void getRateofProfit(Money money, Result result) {
        double rateOfProfit = result.getRateOfProfit(money);
        OutputView.printRateOfProfit(rateOfProfit);
    }
}
