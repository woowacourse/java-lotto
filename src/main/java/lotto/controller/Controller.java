package lotto.controller;

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

public class Controller {

    private final LottosController lottosController = new LottosController();

    public void play() {
        // 로또 구매
        Lottos lottos = lottosController.buyLottos();
        OutputView.printLottos(lottos);
        // 결과 출력
        printResult(lottos);
    }

    private void printResult(Lottos lottos) {
        Money totalMoney = lottos.totalPrice();
        Result result = lottos.getResult(requestWinningLotto());
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
