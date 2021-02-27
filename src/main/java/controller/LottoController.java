package controller;

import domain.*;
import view.InputView;
import view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class LottoController {
    private final InputView inputView = InputView.getInstance();
    private final OutputView outputView = OutputView.getInstance();

    public void run() {
        Money budget = createBudgetMoney();
        Quantity quantity = createQuantity(budget);
        List<LottoTicket> lottoTickets = createLottoTickets(quantity.manual(), quantity.auto());
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

    private Quantity createQuantity(Money budget) {
        try {
            return new Quantity(budget, inputView.scanManualQuantity());
        } catch (RuntimeException e) {
            outputView.printError(e);
            return createQuantity(budget);
        }
    }

    private List<LottoTicket> createLottoTickets(long manualCount, long autoCount) {
        List<LottoTicket> lottoTickets = new ArrayList<>(createManualLottoTickets(manualCount));
        lottoTickets.addAll(createAutoLottoTickets(autoCount));
        outputView.printLottoTicketsCount(manualCount, autoCount);
        outputView.printLottoTickets(lottoTickets);
        return lottoTickets;
    }

    private List<LottoTicket> createManualLottoTickets(long manualCount) {
        List<List<Integer>> manualNumbers = inputView.scanManualNumbers(manualCount);
        return LottoMachine.buyManual(manualNumbers);
    }

    private List<LottoTicket> createAutoLottoTickets(long autoCount) {
        return LottoMachine.buyAuto(autoCount);
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
