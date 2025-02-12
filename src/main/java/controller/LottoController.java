package controller;

import controller.dto.LottoDtoMapper;
import controller.dto.WinningLottoRequest;
import java.util.List;
import model.LottoCustomer;
import model.LottoCustomerHistory;
import model.LottoStore;
import model.LottoTicket;
import model.WinningLotto;
import view.LottoConsoleView;

public class LottoController {

    private final LottoConsoleView lottoConsoleView;
    private final LottoStore lottoStore;
    private final LottoCustomerHistory lottoCustomerHistory;
    private final LottoDtoMapper lottoDtoMapper;

    public LottoController(LottoConsoleView lottoConsoleView, LottoStore lottoStore,
            LottoCustomerHistory lottoCustomerHistory, LottoDtoMapper lottoDtoMapper) {
        this.lottoConsoleView = lottoConsoleView;
        this.lottoStore = lottoStore;
        this.lottoCustomerHistory = lottoCustomerHistory;
        this.lottoDtoMapper = lottoDtoMapper;
    }

    public void run() {
        purchase();

        requestWinningLotto();
    }

    private void purchase() {
        int purchaseAmount = lottoConsoleView.requestPurchaseAmount();
        List<LottoTicket> tickets = lottoStore.purchase(purchaseAmount);
        lottoCustomerHistory.add(new LottoCustomer(tickets, purchaseAmount));

        lottoConsoleView.printPurchaseCount(tickets.size());
        lottoConsoleView.printPurchasedLotto(lottoDtoMapper.toLottoTicketResponse(tickets));
    }

    private void requestWinningLotto() {
        WinningLottoRequest winningLottoRequest = lottoConsoleView.requestWinningLotto();
        WinningLotto winningLotto = lottoDtoMapper.toWinningLotto(winningLottoRequest);
    }
}
