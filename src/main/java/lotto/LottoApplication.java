package lotto;

import java.util.List;
import lotto.controller.LottoManager;
import lotto.model.Ticket;
import lotto.model.Tickets;
import lotto.model.TicketsGenerator;
import lotto.model.LottoResult;
import lotto.model.Money;
import lotto.model.WinNumbers;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoApplication {

    public static void main(String[] args) {
        Money money = setMoney();
        Tickets tickets = new Tickets(getAutoTickets(money.getMoney() / Money.PAYMENT_UNIT));
        WinNumbers winNumbers = getWinNumbersAndBonusBallNumber();
        getBonusBallNumber(winNumbers);

        LottoManager.lotto(tickets, winNumbers);

        printCorrectResults();
        OutputView.printYield(money.getYield());
    }

    private static Money setMoney() {
        OutputView.printInput();
        return new Money(InputView.input());
    }

    private static List<Ticket> getAutoTickets(int ticketsCount) {
        OutputView.printHowManyTicketsPurchase(ticketsCount);
        List<Ticket> tickets = TicketsGenerator.createAutoTickets(ticketsCount);
        OutputView.printAutoNumbers(tickets);
        return tickets;
    }

    private static WinNumbers getWinNumbersAndBonusBallNumber() {
        OutputView.printInputWinNumber();
        return new WinNumbers(InputView.input());
    }

    private static void getBonusBallNumber(WinNumbers winNumbers) {
        OutputView.printInputBonusNumber();
        winNumbers.setBonusBallNumber(InputView.input());
    }

    private static void printCorrectResults() {
        OutputView.printResult();
        for (LottoResult lottoResult : LottoResult.values()) {
            OutputView.printCorrectResult(lottoResult.getCorrect(), lottoResult.getPrize(),
                LottoManager.lottoResultMap.get(lottoResult.name()));
        }
    }
}
