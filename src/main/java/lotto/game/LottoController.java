package lotto.game;

import lotto.money.Money;
import lotto.money.PrizeMoney;
import lotto.ranking.Statistics;
import lotto.ticket.BonusBall;
import lotto.ticket.Ticket;
import lotto.ticket.Tickets;
import lotto.ticket.WinnerTicket;
import lotto.ticket.strategy.ManualNumbersGenerator;
import lotto.ticket.strategy.RandomNumbersGenerator;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class LottoController {
    public void run() {
        Money money = generateMoney();
        LottoCount lottoCount = generateCount(money);
        LottoCount manualTicketAmount = generateManualTicketAmount();
        LottoCount autoTicketAmount = lottoCount.purchaseManualTicket(manualTicketAmount);

        Tickets manualTickets = manualTicketGenerate(manualTicketAmount);
        Tickets autoTickets = autoTicketGenerate(autoTicketAmount);
        OutputView.noticeLottoCount(manualTicketAmount, autoTicketAmount);

        Tickets totalTicket = Tickets.joinTicket(manualTickets, autoTickets);
        OutputView.showTickets(totalTicket);

        WinnerTicket winnerTicket = generateWinnerTicket();
        BonusBall bonusBall = generateBonusBall(winnerTicket);
        Statistics statistics = generateStatistics(totalTicket, winnerTicket, bonusBall);
        makeResult(money, statistics);
    }

    private Tickets autoTicketGenerate(LottoCount count) {
        List<Ticket> tickets = new ArrayList<>();
        while (count.isGreaterThanZero()) {
            count = count.decreaseOne();
            tickets.add(new Ticket(new RandomNumbersGenerator().generate()));
        }
        return new Tickets(tickets);
    }

    private Tickets manualTicketGenerate(LottoCount count) {
        try {
            OutputView.enterManualTicketNumber();
            List<Ticket> tickets = new ArrayList<>();
            ticketGenerate(count, tickets);
            return new Tickets(tickets);
        } catch (IllegalArgumentException e) {
            OutputView.printError(e);
            return manualTicketGenerate(count);
        }
    }

    private void ticketGenerate(LottoCount count, List<Ticket> tickets) {
        while (count.isGreaterThanZero()) {
            count = count.decreaseOne();
            tickets.add(new Ticket(new ManualNumbersGenerator(InputView.inputNumbers()).generate()));
        }
    }

    private Money generateMoney() {
        try {
            OutputView.enterPurchaseMoney();
            return InputView.inputMoney();
        } catch (IllegalArgumentException e) {
            OutputView.printError(e);
            return generateMoney();
        }
    }

    private LottoCount generateCount(Money money) {
        try {
            return new LottoCount(money);
        } catch (IllegalArgumentException e) {
            OutputView.printError(e);
            return generateCount(money);
        }
    }

    private LottoCount generateManualTicketAmount() {
        try {
            OutputView.enterManualTicketAmount();
            return InputView.inputManualTicketAmount();
        } catch (IllegalArgumentException e) {
            OutputView.printError(e);
            return generateManualTicketAmount();
        }
    }

    private WinnerTicket generateWinnerTicket() {
        try {
            OutputView.enterWinnerTicket();
            return InputView.inputWinnerTicket();
        } catch (IllegalArgumentException e) {
            OutputView.printError(e);
            return generateWinnerTicket();
        }
    }

    private BonusBall generateBonusBall(WinnerTicket winnerTicket) {
        try {
            OutputView.enterBonusBall();
            return InputView.inputBonusBall(winnerTicket);
        } catch (IllegalArgumentException e) {
            OutputView.printError(e);
            return generateBonusBall(winnerTicket);
        }
    }

    private Statistics generateStatistics(Tickets tickets, WinnerTicket winnerTicket, BonusBall bonusBall) {
        Statistics statistics = new Statistics(tickets.makeResult(winnerTicket, bonusBall));
        OutputView.noticeStatistics(statistics);
        return statistics;
    }

    private void makeResult(Money money, Statistics statistics) {
        PrizeMoney prizeMoney = new PrizeMoney(statistics);
        OutputView.showProfit(prizeMoney.calculateProfit(money));
    }
}