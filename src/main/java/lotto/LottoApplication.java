package lotto;

import lotto.domain.LottoNumber;
import lotto.domain.LottoTicket;
import lotto.domain.LottoTickets;
import lotto.domain.Money;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoApplication {
    public static void main(String[] args) {
        OutputView.inputMoneyInstruction();
        int quantity = new Money(InputView.getInput()).ticketQuantity();
        OutputView.ticketAmountInstruction(quantity);
        LottoTickets lottoTickets = LottoTickets.createLottoTickets(quantity);
        OutputView.lottoTicketList(lottoTickets);
        OutputView.inputWinningNumberInstruction();
        LottoTicket winningLotto = new LottoTicket(InputView.getWinningNumbers());
        OutputView.inputBonusNumberInstruction();
        LottoNumber bonusNumber = LottoNumber.find(InputView.getInput());
        winningLotto.validateBonusNumber(bonusNumber);
        // compare lottoTickets and winningLotto
        // output statistics
    }
}
