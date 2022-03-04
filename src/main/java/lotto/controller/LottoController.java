package lotto.controller;

import java.util.ArrayList;
import java.util.List;

import lotto.domain.factory.MoneyFactory;
import lotto.domain.lotto.Count;
import lotto.domain.lotto.Lotto;
import lotto.domain.factory.LottoFactory;
import lotto.domain.factory.NumberFactory;
import lotto.domain.lotto.WinningLotto;
import lotto.domain.lotto.Lottos;
import lotto.domain.lotto.Money;
import lotto.domain.lotto.Number;
import lotto.domain.Result;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    public void play() {
        Money money = requestMoney();
        Lottos lottos = requestBuyLottos(money);
        WinningLotto winningLotto = requestWinningLotto();
        printResult(lottos, winningLotto);
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

    private Lottos requestBuyLottos(Money money) {
        Count totalLottoCount = money.countToBuyLotto();
        Count manualLottoCount = requestManualLottoCount(money);
        Count autoLottoCount = totalLottoCount.subtract(manualLottoCount);
        OutputView.printRequestManualLottoNumberUI();
        Lottos lottos = new Lottos(autoLottoCount, requestBuyManualLottoNumbers(manualLottoCount));
        OutputView.printLottos(lottos);
        return lottos;
    }

    private Count requestManualLottoCount(Money money) {
        try {
            String input = InputView.inputCountManualLotto();
            Count count = new Count(Integer.parseInt(input));
            money.validateBuyableLottoCount(count);
            return count;
        } catch (IllegalArgumentException exception) {
            OutputView.printException(exception);
            return requestManualLottoCount(money);
        }
    }

    private List<Lotto> requestBuyManualLottoNumbers(Count manualLottoCount) {
        List<Lotto> lottos = new ArrayList<>();
        int countValue = manualLottoCount.value();
        while (countValue-- > 0) {
            Lotto lotto = requestManualLottoNumber();
            lottos.add(lotto);
        }
        return lottos;
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


    private void printResult(Lottos lottos, WinningLotto winningLotto) {
        Money totalMoney = lottos.totalPrice();
        Result result = lottos.getResult(winningLotto);
        OutputView.printResult(result);
        OutputView.printRateOfProfit(totalMoney.getRateOfProfit(result.getTotalProfit()));
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
