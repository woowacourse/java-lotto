package lotto.controller;

import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.Lotto;
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
        Lottos lottos = new Lottos();
        LottoCounter lottoCounter = getLottoCounter(money);
        lottos.buyLottosByManual(getManualLottos(lottoCounter));
        lottos.buyLottosByAuto(lottoCounter.getAutoLottoCount());
        OutputView.printLottos(lottoCounter, lottos);

        WinningNumbers winningNumbers = getWinningNumbers();
        RankCounter rankCounter = new RankCounter(lottos, winningNumbers);
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

    private List<Lotto> getManualLottos(LottoCounter lottoCounter) {
        try {
            return InputView.InputManualLottos(lottoCounter.getManualLottoCount())
                    .stream()
                    .map(Lotto::generateLottoByManual)
                    .collect(Collectors.toList());
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception.getMessage());
            return getManualLottos(lottoCounter);
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
