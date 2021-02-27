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
    private static final int ONE_TICKET_OF_LOTTO = 1;

    private final InputView inputView = InputView.getInstance();
    private final OutputView outputView = OutputView.getInstance();

    public void run() {
        LottoPurchaseManager lottoPurchaseManager = createLottoPurchaseManager();
        Money investedMoney = lottoPurchaseManager.remainBudget();

        List<LottoTicket> lottoTickets = buyLottosManually(lottoPurchaseManager);
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

    private List<LottoTicket> buyLottosManually(LottoPurchaseManager lottoPurchaseManager) {
        int quantity = Repeater.repeatFunctionOnError(inputView::scanManualLottoQuantity);
        if (!lottoPurchaseManager.canAfford(quantity)) {
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
