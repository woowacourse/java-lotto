package controller;

import domain.*;
import view.InputView;
import view.OutputView;
import util.Repeater;

import java.util.List;

public class LottoController {
    private static final int ONE_TICKET_OF_LOTTO = 1;

    private final InputView inputView = InputView.getInstance();
    private final OutputView outputView = OutputView.getInstance();

    public void run() {
        LottoPurchaseManager lottoPurchaseManager = createLottoPurchaseManager();
        Money investedMoney = lottoPurchaseManager.remainBudget();

        LottoTickets lottoTickets = buyLottosManually(lottoPurchaseManager);
        lottoTickets.addAll(buyLottosAutomatically(lottoPurchaseManager));
        outputView.printLottoTicket(lottoTickets);

        WinningNumbers winningNumber = Repeater.repeatFunctionOnError(this::createWinningNumber);
        LottoResult lottoResult = LottoResult.of(winningNumber, lottoTickets);
        outputView.printLottoResultStatistics(lottoResult.result());

        Profit profit = Profit.of(investedMoney, lottoResult.getReward());
        outputView.printProfit(profit);
    }

    private LottoPurchaseManager createLottoPurchaseManager() {
        return LottoPurchaseManager.from(Repeater.repeatFunctionOnError(() -> Money.from(inputView.scanBudget())));
    }

    private LottoTickets buyLottosManually(LottoPurchaseManager lottoPurchaseManager) {
        int quantity = Repeater.repeatFunctionOnError(inputView::scanManualLottoQuantity);
        if (!lottoPurchaseManager.canAfford(quantity)) {
            outputView.printNotEnoughBudget();
            return buyLottosManually(lottoPurchaseManager);
        }

        LottoTickets lottoTickets = new LottoTickets();
        outputView.requestLottoNumbersMessage();
        for (int i = 0; i < quantity; i++) {
            lottoTickets.add(Repeater.repeatFunctionOnError(() -> {
                List<Integer> lottoNumbers = inputView.scanManualLottoNumbers();
                return lottoPurchaseManager.buyManually(lottoNumbers);
            }));
        }
        return lottoTickets;
    }

    private LottoTickets buyLottosAutomatically(LottoPurchaseManager lottoPurchaseManager) {
        LottoTickets lottoTickets = new LottoTickets();
        while (lottoPurchaseManager.canAfford(ONE_TICKET_OF_LOTTO)) {
            lottoTickets.add(lottoPurchaseManager.buyAutomatically());
        }
        return lottoTickets;
    }

    private WinningNumbers createWinningNumber() {
        LottoTicket winningNumbers = LottoTicket.valueOf(Repeater.repeatFunctionOnError(inputView::scanWinningNumber));
        LottoNumber bonusBall = LottoNumber.from(Repeater.repeatFunctionOnError(inputView::scanBonusBall));
        return WinningNumbers.of(winningNumbers, bonusBall);
    }
}
