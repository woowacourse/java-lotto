package lotto.controller;

import lotto.domain.Accountant;
import lotto.domain.LottoNumber;
import lotto.domain.LottoTicket;
import lotto.domain.LottoTickets;
import lotto.domain.Money;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    public static void run() {
        OutputView.inputMoneyInstruction();
        Money money = new Money(InputView.getInput());
        OutputView.ticketAmountInstruction(money);
        LottoTickets lottoTickets = LottoTickets.createLottoTickets(money);
        OutputView.lottoTicketList(lottoTickets);

        OutputView.inputWinningNumberInstruction();
        LottoTicket winningLotto = new LottoTicket(InputView.getWinningNumbers());
        OutputView.inputBonusNumberInstruction();
        LottoNumber bonusNumber = LottoNumber.find(InputView.getInput());
        winningLotto.validateBonusNumber(bonusNumber);

        OutputView.prizeStatistics(lottoTickets.matchResult(winningLotto, bonusNumber));
        OutputView.profitRate(Accountant.calculate(money));

    }
}
