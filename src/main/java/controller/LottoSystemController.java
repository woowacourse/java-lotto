package controller;

import domain.LottoManager;
import domain.Ranking;
import domain.WinningNumbers;
import domain.WinningResult;
import view.InputView;
import view.OutputView;

import java.util.Arrays;

public class LottoSystemController {

    public void run() {
        LottoManager lottoManager = LottoManager.init(InputView.receivePrice());
        printLottoTicketsInfo(lottoManager);
        WinningResult winningResult = lottoManager.getWinningResult(
            WinningNumbers.valueOf(
                InputView.receiveWinningNumbers(),
                InputView.receiveBonusNumber()
            ));
        printLottoResult(winningResult);
    }

    private void printLottoTicketsInfo(LottoManager lottoManager) {
        OutputView.printNumberOfTickets(lottoManager.getLottoQuantity());
        OutputView.printLottoTickets(lottoManager.getLottoTickets());
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
