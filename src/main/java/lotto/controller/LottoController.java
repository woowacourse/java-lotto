package lotto.controller;

import lotto.domain.customer.Customer;
import lotto.domain.customer.Money;
import lotto.domain.result.OverallResult;
import lotto.domain.ticket.LottoTicketBundle;
import lotto.domain.ticket.WinLottoTicket;
import lotto.service.LottoService;
import lotto.view.InputView;
import lotto.view.OutputView;
import lotto.view.dto.BettingMoneyDTO;
import lotto.view.dto.ManualNumbersDTO;
import lotto.view.dto.NumberOfTicketDTO;

import java.util.List;

public class LottoController {
    private LottoService service = new LottoService();

    public void run() {
        Customer customer = createCustomer();

        LottoTicketBundle ticketBundle = service.createLottoTicketBundle(customer);
        OutputView.printPurchaseStatus(service.createPurchaseStatusDTO(customer));
        OutputView.printLottoTickets(service.convertToLottoTicketDTOS(ticketBundle));

        WinLottoTicket winLottoTicket = service.createWinLottoTicket(InputView.inputWinLottoInfo());

        OverallResult overallResult = service.createOverallResult(winLottoTicket, ticketBundle);
        OutputView.printResult(service.convertToResultDTOS(overallResult), service.createStatisticsDTO(overallResult));
    }

    private Customer createCustomer() {
        Money money = createMoney();

        ManualNumbersDTO manualNumbersDTO = InputView.inputManualNumbers(money.getNumberOfManualTickets());
        List<List<Integer>> manualNumbers = manualNumbersDTO.getManualNumbers();

        return new Customer(money, manualNumbers);
    }

    private Money createMoney() {
        BettingMoneyDTO bettingMoney = InputView.inputBettingMoney();
        int amount = bettingMoney.getBettingMoney();

        NumberOfTicketDTO numberOfTicketDTO = InputView.inputNumberOfManualLotto();
        int numberOfManualTicket = numberOfTicketDTO.getNumberOfTicket();

        return new Money(amount, numberOfManualTicket);
    }
}
