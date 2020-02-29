package lotto.controller;

import lotto.domain.customer.Customer;
import lotto.domain.customer.Money;
import lotto.domain.result.OverallResult;
import lotto.domain.result.rank.Rank;
import lotto.domain.ticket.*;
import lotto.service.LottoService;
import lotto.view.InputView;
import lotto.view.OutputView;
import lotto.view.dto.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LottoController {
    private static final List<LottoMachine> lottoMachines = Arrays.asList(new AutoLottoMachine(), new ManualLottoMachine());
    private LottoService service = new LottoService();

    public void run() {
        Customer customer = createCustomer();

        LottoTicketBundle ticketBundle = createLottoTicketBundle(customer);

        OutputView.printLottoTickets(convertToLottoTicketDTOS(ticketBundle));

        OutputView.printPurchaseStatus(new PurchaseStatusDTO(customer.getMoney().getNumberOfManualTickets(), customer.getMoney().getNumberOfLeftTickets()));

        WinLottoTicket winLottoTicket = createWinLottoTicket();

        OverallResult overallResult = service.createOverallResult(winLottoTicket, ticketBundle);

        OutputView.printResult(convertToResultDTOS(overallResult), new StatisticsDTO(service.computeAnalysis(overallResult)));
    }

    private Customer createCustomer() {
        Money money = createMoney();

        ManualNumbersDTO manualNumbersDTO = new ManualNumbersDTO(InputView.inputManualNumbers(money.getNumberOfManualTickets()));
        List<List<Integer>> manualNumbers = manualNumbersDTO.getManualNumbers();

        return new Customer(money, manualNumbers);
    }

    private Money createMoney() {
        BettingMoneyDTO bettingMoney = new BettingMoneyDTO(InputView.inputBettingMoney());
        int amount = bettingMoney.getBettingMoney();

        NumberOfTicketDTO numberOfTicketDTO = new NumberOfTicketDTO(InputView.inputNumberOfManualLotto());
        int numberOfManualTicket = numberOfTicketDTO.getNumberOfTicket();

        return new Money(amount, numberOfManualTicket);
    }

    private LottoTicketBundle createLottoTicketBundle(Customer customer) {
        List<LottoTicket> lottoTickets = new ArrayList<>();

        for (LottoMachine machine : lottoMachines) {
            lottoTickets.addAll(createLottoTickets(machine, customer));
        }

        return service.createLottoTicketBundle(lottoTickets);
    }

    private List<LottoTicket> createLottoTickets(LottoMachine machine, Customer customer) {
        return service.createLottoTickets(machine, customer);
    }

    private List<LottoTicketDTO> convertToLottoTicketDTOS(LottoTicketBundle lottoTicketBundle) {
        List<LottoTicketDTO> lottoTicketDTOS = new ArrayList<>();

        for (LottoTicket lottoTicket : lottoTicketBundle.getLottoTickets()) {
            lottoTicketDTOS.add(new LottoTicketDTO(lottoTicket));
        }

        return lottoTicketDTOS;
    }

    private WinLottoTicket createWinLottoTicket() {
        List<Integer> inputWinNumbers = InputView.inputWinningNumber();
        int inputBonusNumber = InputView.inputBonusNumber();

        return service.createWinLottoTicket(new WinLottoTicketDTO(inputWinNumbers, inputBonusNumber));
    }

    private List<ResultDTO> convertToResultDTOS(OverallResult overallResult) {
        return Arrays.stream(Rank.values())
                .map(aRank -> new ResultDTO(aRank, overallResult.getNumberOfMatchTickets(aRank)))
                .collect(Collectors.toList());
    }
}
