package controller;

import domain.LottoSystem;
import domain.Ranking;
import domain.WinningNumbers;
import domain.WinningResult;
import view.InputView;
import view.OutputView;

public class LottoSystemController {

    public void run() {
        LottoSystem lottoSystem = LottoSystem.init(InputView.receivePrice());
        OutputView.printNumberOfTickets(lottoSystem.getLottoQuantity());

        OutputView.printLottoTickets(lottoSystem.getLottoTickets());

        WinningResult winningResult = lottoSystem.getWinningResult(
                WinningNumbers.valueOf(
                        InputView.receiveWinningNumbers(),
                        InputView.receiveBonusNumber()
                ));

        OutputView.printRankResultTitle();
        for (Ranking ranking : Ranking.values()) {
            OutputView.printIndividualRankResult(winningResult.countNumberOfRank(ranking));
        }

        OutputView.printTotalProfitRate(winningResult.getProfitRate());
    }
}
