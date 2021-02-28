package controller;

import domain.*;
import view.InputView;
import view.OutputView;

import java.util.Arrays;

public class LottoSystemController {

    public void run() {
        Money money = Money.valueOf(InputView.receiveMoney());
        LottoTickets lottoTickets = LottoTickets.valueOf(money, InputView.receiveManualTickets());

        printLottoTicketsInfo(lottoTickets);

        WinningResult winningResult = calculateWinningResult(lottoTickets, money);
        printLottoResult(winningResult, lottoTickets, money);
    }

    private void printLottoTicketsInfo(LottoTickets lottoTickets) {
        OutputView.printNumberOfTickets(lottoTickets.getLottoQuantity());
        OutputView.printLottoTickets(lottoTickets.toList());
    }

    private void printLottoResult(WinningResult winningResult, LottoTickets lottoTickets, Money money) {
        OutputView.printRankResultTitle();
        Arrays.stream(Ranking.values())
            .filter(ranking -> ranking != Ranking.NOTHING)
            .forEach(ranking ->
                OutputView.printIndividualRankResult(winningResult.countNumberOfRank(ranking), ranking));

        OutputView.printTotalProfitRate(winningResult.calculateProfitRate());
    }

    private WinningResult calculateWinningResult(LottoTickets lottoTickets, Money money) {
        return new WinningResult(
            WinningNumbers.valueOf(
                InputView.receiveWinningNumbers(),
                InputView.receiveBonusNumber()
            ), lottoTickets, money);
    }
}
