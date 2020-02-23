package lotto.controller;

import lotto.domain.*;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

public class LottoGame {
    private LottoGame() {
    }

    public static void main(String[] args) {
        Money purchaseMoney = InputView.inputPurchaseMoney();
        List<LottoTicket> lottoTickets =  LottoFactory.createLottos(purchaseMoney);
        OutputView.printLottos(lottoTickets);

        WinningNumbers winningNumbers = InputView.inputWinningNumbers();
        List<Rank> ranks = winningNumbers.checkOutLottos(lottoTickets);
        Profit profit = new Profit(purchaseMoney, ranks);

        OutputView.printResult(ranks);
        OutputView.printProfit(profit);
    }
}
