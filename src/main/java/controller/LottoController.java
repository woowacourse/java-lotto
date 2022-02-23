package controller;

import java.io.IOException;
import java.util.List;
import model.LottoTicket;
import model.LottoTicketFactory;
import model.Money;
import model.WinningNumbers;
import view.InputView;
import view.OutputView;

public class LottoController {

    public void run() {
        Money inputMoney = inputMoney();

        List<LottoTicket> lottoTickets = purchaseLottoTickets(inputMoney);
        OutputView.printPurchasedTickets(lottoTickets);

        WinningNumbers winningNumbers = inputWinningNumbers();
    }

    private Money inputMoney() {
        try {
            return new Money(InputView.inputMoney());
        } catch (IOException | IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return inputMoney();
        }
    }

    private WinningNumbers inputWinningNumbers() {
        try {
            return WinningNumbers.of(InputView.inputWinningNumbers(), InputView.inputBonusBall());
        } catch (IOException | IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return inputWinningNumbers();
        }
    }

    private List<LottoTicket> purchaseLottoTickets(Money inputMoney) {
        LottoTicketFactory ticketFactory = LottoTicketFactory.getInstance();
        return ticketFactory.createTickets(inputMoney.getAmount());
    }
}
