package controller;

import domain.LottoSystem;
import domain.Ranking;
import domain.WinningNumbers;
import domain.WinningResult;
import view.InputView;
import view.OutputView;

import java.util.Arrays;

public class LottoSystemController {

    public void run() {
        LottoSystem lottoSystem = LottoSystem.init(InputView.receivePrice());
        printLottoTicketsInfo(lottoSystem);
        WinningResult winningResult = lottoSystem.getWinningResult(
            WinningNumbers.valueOf(
                InputView.receiveWinningNumbers(),
                InputView.receiveBonusNumber()
            ));
        printLottoResult(winningResult);
    }

    private void printLottoTicketsInfo(LottoSystem lottoSystem) {
        OutputView.printNumberOfTickets(lottoSystem.getLottoQuantity());
        OutputView.printLottoTickets(lottoSystem.getLottoTickets());
    }

    private void printLottoResult(WinningResult winningResult) {
        OutputView.printRankResultTitle();
        Arrays.stream(Ranking.values())
            .filter(ranking -> ranking != Ranking.NOTHING)
            .forEach(ranking ->
                OutputView.printIndividualRankResult(winningResult.countNumberOfRank(ranking), ranking));

        OutputView.printTotalProfitRate(winningResult.calculateProfitRate());
    }
}
