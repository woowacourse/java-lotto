package lotto.controller;

import java.util.ArrayList;
import java.util.List;

import lotto.domain.lotto.Count;
import lotto.domain.lotto.Lotto;
import lotto.domain.factory.LottoFactory;
import lotto.domain.lotto.LottoCountToBuy;
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
        LottoCountToBuy lottoCountToBuy = LottoCountToBuy.of(money, requestManualLottoCount(money));
        OutputView.printLottoCount(lottoCountToBuy);
        // 개수에 맞게 로또 구매
        OutputView.printRequestManualLottoNumberUI();
        Lottos lottos = requestBuyLottos(lottoCountToBuy);
        OutputView.printLottos(lottos);
        // 당첨 번호와 비교하여 결과 출력
        Result result = lottos.getResult(requestWinningLotto());
        OutputView.printResult(result);
        OutputView.printRateOfProfit(money.getRateOfProfit(result.getTotalProfit()));
    }

    private Money requestMoney() {
        try {
            return new Money(InputView.inputMoney());
        } catch (IllegalArgumentException exception) {
            OutputView.printException(exception);
            return requestMoney();
        }
    }

    private Lottos requestBuyLottos(LottoCountToBuy lottoCountToBuy) {
        List<Lotto> lottos = new ArrayList<>();
        requestBuyManualLottos(lottos, lottoCountToBuy.getManualCount());
        requestBuyAutoLottos(lottos, lottoCountToBuy.getAutoCount());
        return new Lottos(lottos);
    }

    private Count requestManualLottoCount(Money money) {
        try {
            Count count = new Count(InputView.inputCountManualLotto());
            money.validateBuyableLottoCount(count);
            return count;
        } catch (IllegalArgumentException exception) {
            OutputView.printException(exception);
            return requestManualLottoCount(money);
        }
    }

    private void requestBuyManualLottos(List<Lotto> lottos, int manualLottoCount) {
        while (manualLottoCount-- > 0) {
            lottos.add(requestManualLottoNumber());
        }
    }

    private void requestBuyAutoLottos(List<Lotto> lottos, int autoLottoCount) {
        while (autoLottoCount-- > 0) {
            lottos.add(LottoFactory.auto());
        }
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
            return new WinningLotto(lotto, new Number(InputView.inputBonusNumber()));
        } catch (IllegalArgumentException exception) {
            OutputView.printException(exception);
            return requestWinningLottoContainingBonusNumber(lotto);
        }
    }
}
