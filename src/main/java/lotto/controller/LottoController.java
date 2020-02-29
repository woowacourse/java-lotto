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
        int amount = bettingMoney.getBettingMoney();

        NumberOfTicketDTO numberOfTicketDTO = new NumberOfTicketDTO(InputView.inputNumberOfManualLotto());
        int numberOfManualTicket = numberOfTicketDTO.getNumberOfTicket();

        return new Money(amount, numberOfManualTicket);
    }

    private LottoTicketBundle createLottoTicketBundle(Money money) {
        List<LottoTicket> autoTickets = createAutoTickets(money);
        List<LottoTicket> manualTickets = createManualTickets(money);

        OutputView.printPurchaseStatus(new PurchaseStatusDTO(money.getNumberOfManualTickets(), money.getNumberOfLeftTickets()));

        return service.createLottoTicketBundle(manualTickets, autoTickets);
    }

    private List<LottoTicket> createManualTickets(Money money) {
        ManualNumbersDTO manualNumbersDTO = new ManualNumbersDTO(InputView.inputManualNumbers(money.getNumberOfManualTickets()));
        List<List<Integer>> manualNumbers = manualNumbersDTO.getManualNumbers();

        return service.createManualTickets(money, manualNumbers);
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
