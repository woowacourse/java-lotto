package lotto.controller;

import lotto.domain.LottoCounter;
import lotto.domain.Lottos;
import lotto.domain.Money;
import lotto.domain.RankCounter;
import lotto.domain.WinningNumbers;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Controller {

    public void run() {
        Money money = getMoney();
        LottoCounter lottoCounter = getLottoCounter(money);
        Lottos lottos = getLottos(lottoCounter);
        OutputView.printLottos(lottoCounter, lottos);

        RankCounter rankCounter = new RankCounter(lottos, getWinningNumbers());
        OutputView.printWinningStatistic(money, rankCounter);
    }

    private Money getMoney() {
        try {
            int inputMoney = InputView.inputMoney();
            return new Money(inputMoney);
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception.getMessage());
            return getMoney();
        }
    }

    private LottoCounter getLottoCounter(Money money) {
        try {
            return new LottoCounter(money, InputView.inputManualLottoCount());
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception.getMessage());
            return getLottoCounter(money);
        }
    }

    private Lottos getLottos(LottoCounter lottoCounter) {
        try {
            return new Lottos(InputView.inputManualLottos(lottoCounter.getManualLottoCount()),
                    lottoCounter.getAutoLottoCount());
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception.getMessage());
            return getLottos(lottoCounter);
        }
    }

    private WinningNumbers getWinningNumbers() {
        try {
            return new WinningNumbers(InputView.inputWinningLotto(), InputView.inputBonusNumber());
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception.getMessage());
            return getWinningNumbers();
        }
    }
}
