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
        List<LottoTicket> lottoTickets = createLottoTickets(budget);
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

    private List<LottoTicket> createLottoTickets(Money budget) {
        List<LottoTicket> lottoTickets = LottoMachine.buy(budget);
        outputView.printLottoTicketsCount(lottoTickets);
        outputView.printLottoTickets(lottoTickets);
        return lottoTickets;
    }

    private WinningLotto createWinningLotto() {
        try {
            LottoTicket winningNumbers = LottoTicket.of(inputView.scanWinningNumbers());
            LottoNumber bonusBall = new LottoNumber(inputView.scanBonusBall());
            return new WinningLotto(winningNumbers, bonusBall);
        } catch (RuntimeException e) {
            outputView.printError(e);
            return createWinningLotto();
        }
    }
}
