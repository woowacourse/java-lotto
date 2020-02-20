package lotto.controller;

import lotto.domain.*;
import lotto.view.OutputView;
import lotto.view.dto.InputDTO;

import java.util.List;

public class LottoGame {
    public static void main(String[] args) {
        Money purchaseMoney = InputDTO.inputPurchaseMoney();
        List<LottoTicket> lottoTickets =  LottoFactory.createLottos(purchaseMoney);

        OutputView.printLottos(lottoTickets);

        List<LottoNumber> sixNumbers = InputDTO.inputSixNumbers();
        LottoNumber bonusNumber = InputDTO.inputBonusNumber();
        WinningNumbers winningNumbers = new WinningNumbers(sixNumbers, bonusNumber);

        List<Rank> ranks = winningNumbers.compareLottos(lottoTickets);

        OutputView.printResult(ranks);
        OutputView.printProfit(purchaseMoney, ranks);
    }
}
