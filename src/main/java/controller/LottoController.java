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
        LottoPurchasingMoney inputLottoPurchasingMoney = inputMoney();

        List<Lotto> lotteries = purchaseLottoTickets(inputLottoPurchasingMoney);
        OutputView.printPurchasedTickets(lotteries);

        WinningLotto winningLotto = inputWinningNumbers();

        WinningStatistics winningStatistics = calculateStatistics(lotteries, winningLotto);

        OutputView.printStatistics(winningStatistics, inputLottoPurchasingMoney);
    }

    private WinningStatistics calculateStatistics(List<Lotto> lotteries, WinningLotto winningLotto) {
        WinningStatistics winningStatistics = new WinningStatistics();

        for (Lotto lotto : lotteries) {
            LottoRank lottoRank = LottoRank.getRank(
                    winningLotto.countMatching(lotto),
                    winningLotto.containBonusBall(lotto)
            );
            winningStatistics.put(lottoRank);
        }
        return winningStatistics;
    }

    private LottoPurchasingMoney inputMoney() {
        try {
            return LottoPurchasingMoney.valueOf(InputView.inputMoney());
        } catch (IOException | IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return inputMoney();
        }
    }

    private WinningLotto inputWinningNumbers() {
        try {
            return WinningLotto.of(InputView.inputWinningNumbers(), InputView.inputBonusBall());
        } catch (IOException | IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return inputWinningNumbers();
        }
    }

    private List<Lotto> purchaseLottoTickets(LottoPurchasingMoney inputLottoPurchasingMoney) {
        LottoFactory ticketFactory = LottoFactory.getInstance();
        return ticketFactory.generateLotteries(inputLottoPurchasingMoney.getAmount());
    }
}
