package lotto.controller;

import lotto.domain.customer.Customer;
import lotto.domain.customer.PurchaseInfo;
import lotto.domain.result.OverallResult;
import lotto.domain.ticket.LottoTicketBundle;
import lotto.domain.ticket.WinLottoTicket;
import lotto.service.LottoService;
import lotto.view.InputView;
import lotto.view.OutputView;
import lotto.view.dto.ManualNumbersDTO;

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
        PurchaseInfo purchaseInfo = createPurchaseInfo();

        ManualNumbersDTO manualNumbersDTO = InputView.inputManualNumbers(purchaseInfo.getNumberOfManualTickets());
        List<List<Integer>> manualNumbers = manualNumbersDTO.getManualNumbers();

        return new Customer(purchaseInfo, manualNumbers);
    }

    private PurchaseInfo createPurchaseInfo() {
        int bettingMoney = InputView.inputBettingMoney().getBettingMoney();
        int numberOfManualTicket = InputView.inputNumberOfManualLotto().getNumberOfTicket();

        return new PurchaseInfo(bettingMoney, numberOfManualTicket);
    }
}
