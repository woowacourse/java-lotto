package controller;

import domain.LottoTicket;
import domain.Money;
import domain.WinningNumbers;
import domain.LottoResult;
import domain.Profit;
import domain.LottoNumber;
import domain.LottoPurchaseManager;
import view.InputView;
import view.OutputView;
import util.Repeater;

import java.util.ArrayList;
import java.util.List;

public class LottoController {
    private final InputView inputView = InputView.getInstance();
    private final OutputView outputView = OutputView.getInstance();

    public void run() {
        LottoPurchaseManager lottoPurchaseManager = createLottoPurchase();
        Money investedMoney = lottoPurchaseManager.remainBudget();

        List<LottoTicket> lottoTickets = buyLottosManually(lottoPurchaseManager);
        lottoTickets.addAll(buyLottosAutomatically(lottoPurchaseManager));
        outputView.printLottoTicket(lottoTickets);

        WinningNumbers winningNumber = Repeater.repeatFunctionOnError(this::createWinningNumber);
        LottoResult lottoResult = LottoResult.of(winningNumber, lottoTickets);
        outputView.printLottoResultStatistics(lottoResult.result());

        Profit profit = new Profit(investedMoney, lottoResult.getReward());
        outputView.printProfit(profit);
    }

    private LottoPurchaseManager createLottoPurchase() {
        return new LottoPurchaseManager(Repeater.repeatFunctionOnError(() -> new Money(inputView.scanBudget())));
    }

    private List<LottoTicket> buyLottosManually(LottoPurchaseManager lottoPurchaseManager) {
        int quantity = Repeater.repeatFunctionOnError(inputView::scanManualLottoQuantity);
        if (!lottoPurchaseManager.canAfford(LottoTicket.PRICE, quantity)) {
            outputView.printNotEnoughBudget();
            return buyLottosManually(lottoPurchaseManager);
        }

        List<LottoTicket> lottoTickets = new ArrayList<>();
        outputView.requestLottoNumbersMessage();
        for (int i = 0; i < quantity; i++) {
            lottoTickets.add(Repeater.repeatFunctionOnError(() -> {
                List<Integer> lottoNumbers = inputView.scanManualLottoNumbers();
                return lottoPurchaseManager.buyManually(lottoNumbers);
            }));
        }
        return lottoTickets;
    }

    private List<LottoTicket> buyLottosAutomatically(LottoPurchaseManager lottoPurchaseManager) {
        List<LottoTicket> lottoTickets = new ArrayList<>();
        while (lottoPurchaseManager.canAfford(LottoTicket.PRICE, 1)) {
            lottoTickets.add(lottoPurchaseManager.buyAutomatically());
        }
        return lottoTickets;
    }

    private WinningNumbers createWinningNumber() {
        LottoTicket winningNumbers = LottoTicket.valueOf(Repeater.repeatFunctionOnError(inputView::scanWinningNumber));
        LottoNumber bonusBall = new LottoNumber(Repeater.repeatFunctionOnError(inputView::scanBonusBall));
        return new WinningNumbers(winningNumbers, bonusBall);
    }
}
