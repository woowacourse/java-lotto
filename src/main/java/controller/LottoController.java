package controller;

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
        LottoPurchase lottoPurchase = createLottoPurchase();
        Money investedMoney = lottoPurchase.remainBudget();

        List<LottoTicket> lottoTickets = buyLottosManually(lottoPurchase);
        lottoTickets.addAll(buyLottosAutomatically(lottoPurchase));
        outputView.printLottoTicket(lottoTickets);

        WinningNumbers winningNumber = Repeater.repeatFunctionOnError(this::createWinningNumber);
        Statistics statistics = new Statistics(winningNumber, lottoTickets);
        outputView.printStatistics(statistics);

        Profit profit = new Profit(investedMoney, statistics.getReward());
        outputView.printProfit(profit);
    }

    private LottoPurchase createLottoPurchase() {
        return new LottoPurchase(Repeater.repeatFunctionOnError(() -> new Money(inputView.scanBudget())));
    }

    private List<LottoTicket> buyLottosManually(LottoPurchase lottoPurchase) {
        int quantity = Repeater.repeatFunctionOnError(inputView::scanManualLottoQuantity);
        if (!lottoPurchase.canAfford(LottoTicket.PRICE, quantity)) {
            outputView.printNotEnoughBudget();
            return buyLottosManually(lottoPurchase);
        }

        List<LottoTicket> lottoTickets = new ArrayList<>();
        outputView.requestLottoNumbersMessage();
        for (int i = 0; i < quantity; i++) {
            lottoTickets.add(Repeater.repeatFunctionOnError(() -> {
                List<Integer> lottoNumbers = inputView.scanManualLottoNumbers();
                return lottoPurchase.buyManually(lottoNumbers);
            }));
        }
        return lottoTickets;
    }

    private List<LottoTicket> buyLottosAutomatically(LottoPurchase lottoPurchase) {
        List<LottoTicket> lottoTickets = new ArrayList<>();
        while (lottoPurchase.canAfford(LottoTicket.PRICE, 1)) {
            lottoTickets.add(lottoPurchase.buyAutomatically());
        }
        return lottoTickets;
    }

    private WinningNumbers createWinningNumber() {
        LottoTicket winningNumbers = LottoTicket.valueOf(Repeater.repeatFunctionOnError(inputView::scanWinningNumber));
        LottoNumber bonusBall = new LottoNumber(Repeater.repeatFunctionOnError(inputView::scanBonusBall));
        return new WinningNumbers(winningNumbers, bonusBall);
    }
}
