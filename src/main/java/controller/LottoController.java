package controller;

import domain.*;
import view.InputView;
import view.OutputView;

public class LottoController {
    private final InputView inputView = InputView.getInstance();
    private final OutputView outputView = OutputView.getInstance();

    public void run() {
        Money budget = createBudgetMoney();
        LottoTickets lottoTickets = LottoPurchase.buy(budget);
        outputView.printLottoTicket(lottoTickets);
        outputView.newLine();
        WinningNumber winningNumber = createWinningNumber();
        outputView.newLine();
        Statistics statistics = new Statistics(winningNumber, lottoTickets);
        Profit profit = new Profit(budget, statistics.getReward());
        outputView.printStatistics(statistics);
    }

    private WinningNumber createWinningNumber() {
        try {
            LottoTicket winningNumbers = LottoTicket.valueOf(inputView.scanWinningNumber());
            LottoNumber bonusBall = new LottoNumber(inputView.scanBonusBall());
            return new WinningNumber(winningNumbers, bonusBall);
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
