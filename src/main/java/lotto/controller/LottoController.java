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
        // 구매 금액 입력
        Money money = requestMoney();
        // 수동, 자동 로또 개수 설정
        Count totalLottoCount = money.countToBuyLotto();
        Count manualLottoCount = requestManualLottoCount(money);
        Count autoLottoCount = totalLottoCount.subtract(manualLottoCount);
        OutputView.printLottoCount(manualLottoCount, autoLottoCount);
        // 개수에 맞게 로또 구매
        OutputView.printRequestManualLottoNumberUI();
        Lottos lottos = requestBuyLottos(manualLottoCount, autoLottoCount);
        OutputView.printLottos(lottos);
        // 당첨 번호와 비교하여 결과 출력
        Result result = lottos.getResult(requestWinningLotto());
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

    private Lottos requestBuyLottos(Count manualLottoCount, Count autoLottoCount) {
        List<Lotto> lottos = new ArrayList<>();
        requestBuyManualLottos(lottos, manualLottoCount);
        requestBuyAutoLottos(lottos, autoLottoCount);
        return new Lottos(lottos);
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

    private List<Lotto> requestBuyManualLottos(List<Lotto> lottos, Count manualLottoCount) {
        manualLottoCount.play(lottos, item ->
            item.add(requestManualLottoNumber()));
        return lottos;
    }

    private List<Lotto> requestBuyAutoLottos(List<Lotto> lottos, Count autoLottoCount) {
        autoLottoCount.play(lottos, item ->
            item.add(LottoFactory.auto()));
        return lottos;
    }

    private Lotto requestManualLottoNumber() {
        try {
            String text = InputView.inputManualLottoNumber();
            return LottoFactory.manual(text);
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
            return LottoFactory.manual(input);
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
