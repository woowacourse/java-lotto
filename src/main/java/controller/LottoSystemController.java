package controller;

import domain.LottoSystem;
import domain.Ranking;
import domain.WinningNumbers;
import domain.WinningResult;
import view.InputView;
import view.OutputView;

import java.util.Arrays;

public class LottoSystemController {
    private LottoSystem lottoSystem;
    private WinningResult winningResult;

    public void run() {
        buyLottoTickets();
        decideWinningNumbers();
        calculateResult();
    }

    private void buyLottoTickets() {
        lottoSystem = LottoSystem.init(InputView.receivePrice());
        OutputView.printNumberOfTickets(lottoSystem.getLottoQuantity());
        OutputView.printLottoTickets(lottoSystem.getLottoTickets());
    }

    private void decideWinningNumbers() {
        winningResult = lottoSystem.getWinningResult(
                WinningNumbers.valueOf(
                        InputView.receiveWinningNumbers(),
                        InputView.receiveBonusNumber()
                ));
    }

    private void calculateResult() {
        OutputView.printRankResultTitle();
        Arrays.stream(Ranking.values())
                .filter(ranking -> ranking != Ranking.NOTHING)
                .forEach(ranking -> OutputView.printIndividualRankResult(
                        winningResult.countNumberOfRank(ranking), ranking)
                );

        OutputView.printTotalProfitRate(winningResult.getProfitRate());
    }
}
