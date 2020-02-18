package lotto;

import lotto.domain.LottoTickets;
import lotto.domain.Money;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoApplication {
    public static void main(String[] args) {
        OutputView.inputMoneyInstruction();
        int quantity = new Money(InputView.getInput()).ticketQuantity();
        OutputView.ticketAmountInstruction(quantity);
        OutputView.lottoTicketList(LottoTickets.createLottoTickets(quantity));
    }
}
