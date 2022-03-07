package lotto.controller;

import lotto.domain.LottoCounter;
import lotto.domain.Lottos;
import lotto.domain.LottoPurchaseMoney;
import lotto.domain.RankCounter;
import lotto.domain.WinningNumbers;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Controller {

    public void run() {
        LottoPurchaseMoney lottoPurchaseMoney = getLottoPurchaseMoney();
        LottoCounter lottoCounter = getLottoCounter(lottoPurchaseMoney);
        Lottos lottos = getLottos(lottoCounter);
        OutputView.printLottos(lottoCounter, lottos);

        RankCounter rankCounter = RankCounter.newInstance(lottos, getWinningNumbers());
        OutputView.printWinningStatistic(lottoPurchaseMoney, rankCounter);
    }

    private LottoPurchaseMoney getLottoPurchaseMoney() {
        try {
            int inputLottoPurchaseMoney = InputView.inputLottoPurchaseMoney();
            return new LottoPurchaseMoney(inputLottoPurchaseMoney);
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception.getMessage());
            return getLottoPurchaseMoney();
        }
    }

    private LottoCounter getLottoCounter(LottoPurchaseMoney lottoPurchaseMoney) {
        try {
            return new LottoCounter(lottoPurchaseMoney, InputView.inputManualLottoCount());
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception.getMessage());
            return getLottoCounter(lottoPurchaseMoney);
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
