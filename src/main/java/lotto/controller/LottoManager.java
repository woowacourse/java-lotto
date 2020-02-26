package lotto.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import lotto.model.*;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoManager {

    public void lotto() {
        Money money = setMoney();
        ManualCount manualCount = getManualNumber(money.getTicketCount());

        Tickets tickets = new Tickets(getTickets(manualCount.getManualCount(),
            money.getTicketCount() - manualCount.getManualCount()));
        WinLottoNumbers winLottoNumbers = getWinNumbersAndBonusBallNumber();
        LottoResult lottoResult = new LottoResult();

        confirmLottoResult(tickets, winLottoNumbers, lottoResult);

        printCorrectResults(lottoResult);
        OutputView.printYield(money.getYield(lottoResult));
    }

    private Money setMoney() {
        OutputView.printInputMoney();
        return new Money(InputView.input());
    }

    private ManualCount getManualNumber(int ticketCount) {
        OutputView.printInputManualNumber();
        return new ManualCount(InputView.input(), ticketCount);
    }

    private List<Ticket> getTickets(int manualCount, int autoCount) {
        List<Ticket> tickets = new ArrayList<>();
        tickets.addAll(getManualTickets(manualCount));
        tickets.addAll(getAutoTickets(autoCount));
        OutputView.printHowManyTicketsPurchase(manualCount, autoCount);
        OutputView.printAutoNumbers(tickets);
        return tickets;
    }

    private List<Ticket> getManualTickets(int manualCount) {
        OutputView.printInputManualLottoNumber();
        List<Ticket> manualTickets = new ArrayList<>();
        for (int i = 0; i < manualCount; i++) {
            manualTickets.add(new Ticket(InputView.input()));
        }
        return manualTickets;
    }

    private List<Ticket> getAutoTickets(int autoCount) {
        return TicketsGenerator.createAutoTickets(autoCount);
    }

    private WinLottoNumbers getWinNumbersAndBonusBallNumber() {
        OutputView.printInputWinNumber();
        String winNumber = InputView.input();
        OutputView.printInputBonusNumber();
        String bonusBallNumber = InputView.input();
        return new WinLottoNumbers(winNumber, bonusBallNumber);
    }

    private void confirmLottoResult(Tickets tickets, WinLottoNumbers winLottoNumbers,
        LottoResult lottoResult) {
        for (Ticket ticket : tickets.getTickets()) {
            RankType rankType = RankType.findLottoResult(winLottoNumbers.matchCount(ticket),
                ticket.contains(winLottoNumbers.getBonusBallNumber()));
            lottoResult.updateResultCount(rankType);
        }
    }

    private void printCorrectResults(LottoResult lottoResult) {
        OutputView.printResult();
        Arrays.stream(RankType.values())
            .filter(rankType -> rankType != RankType.NONE)
            .forEach(rankType -> OutputView
                .printCorrectResult(rankType.getCorrect(), rankType.getPrize(),
                    lottoResult.getResultCount(rankType)));
    }
}