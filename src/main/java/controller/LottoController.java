package controller;

import java.io.IOException;
import java.util.List;
import model.Lotto;
import model.LottoFactory;
import model.LottoPurchasingMoney;
import model.LottoRank;
import model.WinningLotto;
import model.WinningStatistics;
import view.InputView;
import view.OutputView;

public class LottoController {
    public void run() {
        LottoPurchasingMoney inputLottoPurchasingMoney = inputPurchasingMoney();

        List<Lotto> lotteries = purchaseLotteries(inputLottoPurchasingMoney);
        OutputView.printPurchasedLotteries(lotteries);

        WinningLotto winningLotto = inputWinningLotto();

        WinningStatistics winningStatistics = calculateStatistics(lotteries, winningLotto);

        OutputView.printStatistics(winningStatistics, inputLottoPurchasingMoney);
    }

    private LottoPurchasingMoney inputPurchasingMoney() {
        try {
            return LottoPurchasingMoney.valueOf(InputView.inputMoney());
        } catch (IOException | IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return inputPurchasingMoney();
        }
    }

    private List<Lotto> purchaseLotteries(LottoPurchasingMoney inputLottoPurchasingMoney) {
        LottoFactory lottoFactory = LottoFactory.getInstance();
        return lottoFactory.generateLotteries(inputLottoPurchasingMoney);
    }

    private WinningLotto inputWinningLotto() {
        try {
            return WinningLotto.of(InputView.inputWinningNumbers(), InputView.inputBonusBall());
        } catch (IOException | IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return inputWinningLotto();
        }
    }

    private WinningStatistics calculateStatistics(List<Lotto> lotteries, WinningLotto winningLotto) {
        WinningStatistics winningStatistics = new WinningStatistics();

        lotteries.forEach(lotto -> {
            LottoRank lottoRank = LottoRank.getRank(
                    winningLotto.countMatching(lotto),
                    winningLotto.containBonusBall(lotto)
            );
            winningStatistics.put(lottoRank);
        });

        return winningStatistics;
    }
}
