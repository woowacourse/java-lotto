package controller;

import domain.*;
import view.InputView;
import view.OutputView;

public class LottoController {
    private final InputView inputView = InputView.getInstance();
    private final OutputView outputView = OutputView.getInstance();

    public void run() {
        Money budget = createBudgetMoney();
        LottoTickets lottoTickets = createLottoTickets(budget);
        WinningLotto winningLotto = createWinningLotto();

        Statistics statistics = new Statistics(winningLotto, lottoTickets);
        Profit profit = new Profit(budget, statistics.getReward());

        outputView.printStatistics(statistics);
        outputView.printProfit(profit);
    }

    private Money createBudgetMoney() {
        try {
            return new Money(inputView.scanBudget());
        } catch (RuntimeException e) {
            outputView.printError(e);
            return createBudgetMoney();
        }
    }

    private LottoTickets createLottoTickets(Money budget) {
        LottoTickets lottoTickets = LottoMachine.buy(budget);
        outputView.printLottoTicket(lottoTickets);
        outputView.newLine();
        return lottoTickets;
    }

    private WinningLotto createWinningLotto() {
        try {
            LottoTicket winningNumbers = LottoTicket.valueOf(inputView.scanWinningNumbers());
            LottoNumber bonusBall = new LottoNumber(inputView.scanBonusBall());
            outputView.newLine();
            return new WinningLotto(winningNumbers, bonusBall);
        } catch (RuntimeException e) {
            outputView.printError(e);
            return createWinningLotto();
        }
    }
}
