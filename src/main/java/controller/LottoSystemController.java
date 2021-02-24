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
        printLottoResult(lottoManager);
    }

    private void printLottoTicketsInfo(LottoManager lottoManager) {
        OutputView.printNumberOfTickets(lottoManager.getLottoQuantity());
        OutputView.printLottoTickets(lottoManager.getLottoTickets());
    }

    private WinningResult calculateWinningResult(LottoManager lottoManager) {
        return lottoManager.getWinningResult(
            WinningNumbers.valueOf(
                InputView.receiveWinningNumbers(),
                InputView.receiveBonusNumber()
            ));
    }

    private void printLottoResult(LottoManager lottoManager) {
        WinningResult winningResult = calculateWinningResult(lottoManager);
        OutputView.printRankResultTitle();
        Arrays.stream(Ranking.values())
            .filter(ranking -> ranking != Ranking.NOTHING)
            .forEach(ranking ->
                OutputView.printIndividualRankResult(winningResult.countNumberOfRank(ranking), ranking));

        OutputView.printTotalProfitRate(winningResult.calculateProfitRate());
    }
}
