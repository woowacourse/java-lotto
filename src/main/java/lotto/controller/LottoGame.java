package lotto.controller;

import lotto.domain.*;
import lotto.view.OutputView;
import lotto.view.dto.InputDTO;

import java.util.List;

public class LottoGame {
    private LottoGame() {
    }

    public static void main(String[] args) {
        Money purchaseMoney = InputDTO.inputPurchaseMoney();
        List<LottoTicket> lottoTickets =  LottoFactory.createLottos(purchaseMoney);
        OutputView.printLottos(lottoTickets);

        WinningNumbers winningNumbers = InputDTO.inputWinningNumbers();
        List<Rank> ranks = winningNumbers.compareLottos(lottoTickets);

        OutputView.printResult(ranks);
        OutputView.printProfit(purchaseMoney, ranks);
    }
}
