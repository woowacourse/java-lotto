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
        String input;
        Money money;

        do {
            input = InputView.inputMoney();
            money = getValidMoney(input);
        } while (money == null);
        return money;
    }

    private Money getValidMoney(String input) {
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
        boolean lottoIsNull = true;
        Lotto lotto;

        do {
            String input = InputView.inputLastWeekWinningNumbers();
            lotto = toLotto(input.split(", "));
            lottoIsNull = (lotto == null);
        } while (lottoIsNull);

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
        boolean isDuplicate = false;
        Number number;

        do {
            String input = InputView.inputBonusNumber();
            number = toNumber(input);
            isDuplicate = lotto.contains(number);
        } while (number == null || isDuplicate);

        return number;
    }

    private Number toNumber(String input) {
        try {
            return new Number(input);
        } catch (IllegalArgumentException exception) {
            OutputView.printException(exception);
        }
        return null;
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
