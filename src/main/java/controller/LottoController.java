package controller;

import domain.Budget;
import domain.LottoTicket;
import domain.Money;
import domain.WinningNumbers;
import domain.Statistics;
import domain.Profit;
import domain.LottoNumber;
import domain.LottoPurchase;
import view.InputView;
import view.OutputView;
import util.Repeater;

import java.util.ArrayList;
import java.util.List;

public class LottoController {
    private final InputView inputView = InputView.getInstance();
    private final OutputView outputView = OutputView.getInstance();

    public void run() {
        Budget budget = Repeater.repeatFunctionOnError(this::createBudget);
        Money investedMoney = budget.remain();
        List<LottoTicket> lottoTickets = buyLottosAutomatically(budget);
        outputView.printLottoTicket(lottoTickets);
        WinningNumbers winningNumber = Repeater.repeatFunctionOnError(this::createWinningNumber);
        Statistics statistics = new Statistics(winningNumber, lottoTickets);
        Profit profit = new Profit(investedMoney, statistics.getReward());
        outputView.printStatistics(statistics);
        outputView.printProfit(profit);
    }

    private List<LottoTicket> buyLottosAutomatically(Budget budget) {
        List<LottoTicket> lottoTickets = new ArrayList<>();
        while (budget.canAfford(LottoTicket.PRICE, 1)) {
            lottoTickets.add(LottoPurchase.buyAutomatically(budget));
        }
        return lottoTickets;
    }

    private WinningNumbers createWinningNumber() {
        LottoTicket winningNumbers = LottoTicket.valueOf(Repeater.repeatFunctionOnError(inputView::scanWinningNumber));
        LottoNumber bonusBall = new LottoNumber(Repeater.repeatFunctionOnError(inputView::scanBonusBall));
        return new WinningNumbers(winningNumbers, bonusBall);
    }

    private Budget createBudget() {
        return new Budget(new Money(Repeater.repeatFunctionOnError(inputView::scanBudget)));
    }
}
