package controller;

import domain.*;
import view.InputView;
import view.OutputView;
import view.ScanRepeater;

import java.util.ArrayList;
import java.util.List;

public class LottoController {
    private final InputView inputView = InputView.getInstance();
    private final OutputView outputView = OutputView.getInstance();

    public void run() {
        Budget budget = createBudget();
        Money investedMoney = budget.remain();
        List<LottoTicket> lottoTickets = buyLottosAutomatically(budget);
        outputView.printLottoTicket(lottoTickets);
        WinningNumbers winningNumber = createWinningNumber();
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
        try {
            LottoTicket winningNumbers = LottoTicket.valueOf(ScanRepeater.scanOrRepeatOnError(inputView::scanWinningNumber));
            LottoNumber bonusBall = new LottoNumber(ScanRepeater.scanOrRepeatOnError(inputView::scanBonusBall));
            return new WinningNumbers(winningNumbers, bonusBall);
        } catch (RuntimeException e) {
            outputView.printError(e);
            return createWinningNumber();
        }
    }

    private Budget createBudget() {
        try {
            return new Budget(new Money(ScanRepeater.scanOrRepeatOnError(inputView::scanBudget)));
        } catch (RuntimeException e) {
            outputView.printError(e);
            return createBudget();
        }
    }
}
