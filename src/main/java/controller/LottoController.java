package controller;

import domain.*;
import view.InputView;
import view.OutputView;

import java.util.List;

public class LottoController {
    private final InputView inputView = InputView.getInstance();
    private final OutputView outputView = OutputView.getInstance();

    public void run() {
        Money budget = createBudgetMoney();
        List<LottoTicket> lottoTickets = LottoPurchase.buy(budget);
        outputView.printLottoTicket(lottoTickets);
        WinningNumbers winningNumber = createWinningNumber();
        Statistics statistics = new Statistics(winningNumber, lottoTickets);
        Profit profit = new Profit(budget, statistics.getReward());
        outputView.printStatistics(statistics);
        outputView.printProfit(profit);
    }

    private WinningNumbers createWinningNumber() {
        try {
            LottoTicket winningNumbers = LottoTicket.valueOf(inputView.scanWinningNumber());
            LottoNumber bonusBall = new LottoNumber(inputView.scanBonusBall());
            return new WinningNumbers(winningNumbers, bonusBall);
        } catch (RuntimeException e) {
            outputView.printError(e);
            return createWinningNumber();
        }
    }

    private Money createBudgetMoney() {
        try {
            return new Money(inputView.scanBudget());
        } catch (RuntimeException e) {
            outputView.printError(e);
            return createBudgetMoney();
        }
    }
}
