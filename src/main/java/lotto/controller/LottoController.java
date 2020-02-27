package lotto.controller;

import lotto.domain.Money;
import lotto.domain.result.OverallResult;
import lotto.domain.result.rank.Rank;
import lotto.domain.ticket.AutoLottoMachine;
import lotto.domain.ticket.LottoTicket;
import lotto.domain.ticket.LottoTicketBundle;
import lotto.domain.ticket.WinLottoTicket;
import lotto.service.LottoService;
import lotto.view.InputView;
import lotto.view.OutputView;
import lotto.view.dto.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LottoController {
    private LottoService service = new LottoService(new AutoLottoMachine());

    public void run() {
        Money money = createMoney();

        LottoTicketBundle ticketBundle = createLottoTicketBundle(money);

        OutputView.printLottoTickets(convertToLottoTicketDTOS(ticketBundle));

        WinLottoTicket winLottoTicket = createWinLottoTicket();

        OverallResult overallResult = service.createOverallResult(winLottoTicket, ticketBundle);

        OutputView.printResult(convertToResultDTOS(overallResult), new StatisticsDTO(service.computeAnalysis(overallResult)));
    }

    private Money createMoney() {
        BettingMoneyDTO bettingMoney = new BettingMoneyDTO(InputView.inputBettingMoney());
        return new Money(bettingMoney.getBettingMoney());
    }

    private LottoTicketBundle createLottoTicketBundle(Money money) {
        List<LottoTicket> manualTickets = createManualTickets(money);
        List<LottoTicket> autoTickets = createAutoTickets(money);

        OutputView.printPurchaseStatus(new PurchaseStatusDTO(manualTickets.size(), autoTickets.size()));

        return service.createLottoTicketBundle(manualTickets, autoTickets);
    }

    private List<LottoTicket> createManualTickets(Money money) {
        NumberOfTicketDTO numberOfTicketDTO = new NumberOfTicketDTO(InputView.inputNumberOfManualLotto());
        int numberOfManualTicket = numberOfTicketDTO.getNumberOfTicket();

        ManualNumbersDTO manualNumbersDTO = new ManualNumbersDTO(InputView.inputManualNumbers(numberOfManualTicket));
        List<List<Integer>> manualNumbers = manualNumbersDTO.getLists();

        return service.createManualTickets(money, numberOfManualTicket, manualNumbers);
    }

    private List<LottoTicket> createAutoTickets(Money money) {
        return service.createAutoTickets(money);
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

        WinLottoTicketDTO winLottoTicketDTO = new WinLottoTicketDTO(inputWinNumbers, inputBonusNumber);

        return service.createWinLottoTicket(winLottoTicketDTO.getWinNumbers(), winLottoTicketDTO.getBonusNumber());
    }

    private List<ResultDTO> convertToResultDTOS(OverallResult overallResult) {
        return Arrays.stream(Rank.values())
                .map(aRank -> new ResultDTO(aRank, overallResult.getNumberOfMatchTickets(aRank)))
                .collect(Collectors.toList());
    }
}
