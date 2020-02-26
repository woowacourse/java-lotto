package lotto.controller;

import java.util.Arrays;
import java.util.List;


import lotto.model.*;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoManager {

    public void lotto() {
        Money money = setMoney();
        Tickets tickets = new Tickets(getAutoTickets(money.getMoney() / Money.PAYMENT_UNIT));
        WinNumbers winNumbers = getWinNumbersAndBonusBallNumber();
        LottoResult lottoResult = new LottoResult();

        confirmLottoResult(tickets, winNumbers, lottoResult);

        printCorrectResults(lottoResult);
        OutputView.printYield(money.getYield(lottoResult));
    }

    private Money setMoney() {
        OutputView.printInput();
        return new Money(InputView.input());
    }

    private List<Ticket> getAutoTickets(int ticketsCount) {
        OutputView.printHowManyTicketsPurchase(ticketsCount);
        List<Ticket> tickets = TicketsGenerator.createAutoTickets(ticketsCount);
        OutputView.printAutoNumbers(tickets);
        return tickets;
    }

    private WinNumbers getWinNumbersAndBonusBallNumber() {
        OutputView.printInputWinNumber();
        String winNumber = InputView.input();
        OutputView.printInputBonusNumber();
        String bonusBallNumber = InputView.input();
        return new WinNumbers(winNumber, bonusBallNumber);
    }

    private void confirmLottoResult(Tickets tickets, WinNumbers winNumbers, LottoResult lottoResult) {
        for (Ticket ticket : tickets.getTickets()) {
            RankType rankType = RankType.findLottoResult(winNumbers.matchCount(ticket),
                ticket.contains(winNumbers.getBonusBallNumber()));
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