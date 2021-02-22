package lotto.game;

import lotto.money.Money;
import lotto.money.PrizeMoney;
import lotto.ranking.Statistics;
import lotto.ticket.BonusBall;
import lotto.ticket.Ticket;
import lotto.ticket.Tickets;
import lotto.ticket.WinnerTicket;
import lotto.ticket.strategy.ManualNumbersGenerator;
import lotto.ticket.strategy.NumbersGenerator;
import lotto.ticket.strategy.RandomNumbersGenerator;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    public void run() {
        Money money = generateMoney();
        LottoCount lottoCount = generateCount(money);
        LottoCount manualTicketAmount = generateManualTicketAmount();
        LottoCount autoTicketAmount = lottoCount.purchaseManualTicket(manualTicketAmount);

        Tickets manualTickets = purchaseManualTickets(manualTicketAmount);
        Tickets autoTickets = generateAutoTicket(autoTicketAmount);
        OutputView.noticeLottoCount(manualTicketAmount, autoTicketAmount);

        Tickets totalTicket = new Tickets(manualTickets, autoTickets);
        OutputView.showTickets(totalTicket);

        WinnerTicket winnerTicket = generateWinnerTicket();
        BonusBall bonusBall = generateBonusBall(winnerTicket);
        Statistics statistics = generateStatistics(totalTicket, winnerTicket, bonusBall);
        makeResult(money, statistics);
    }

    private Tickets purchaseManualTickets(LottoCount value) {
        Tickets tickets = new Tickets();
        try {
            OutputView.enterManualTicketNumber();
            eachManualTicketGenerate(tickets, value);
            return tickets;
        } catch (IllegalArgumentException e) {
            OutputView.printError(e);
            return purchaseManualTickets(value);
        }
    }

    private void eachManualTicketGenerate(Tickets tickets, LottoCount count) {
        while (count.isGreaterThanZero()) {
            count = count.decreaseOne();
            NumbersGenerator numbersGenerator = new ManualNumbersGenerator(InputView.inputNumbers());
            tickets.add(new Ticket(numbersGenerator.generate()));
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

    private Tickets generateAutoTicket(LottoCount lottoCount) {
        return new Tickets(lottoCount, new RandomNumbersGenerator());
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