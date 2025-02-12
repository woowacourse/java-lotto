package controller;

import controller.dto.LottoDtoMapper;
import java.util.List;
import model.LottoCustomer;
import model.LottoCustomerHistory;
import model.LottoStore;
import model.LottoTicket;
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
    }

    private void purchase() {
        int purchaseAmount = lottoConsoleView.requestPurchaseAmount();
        List<LottoTicket> tickets = lottoStore.buy(purchaseAmount);
        lottoCustomerHistory.add(new LottoCustomer(tickets, purchaseAmount));

        lottoConsoleView.printPurchaseCount(tickets.size());
        lottoConsoleView.printPurchasedLotto(lottoDtoMapper.toLottoTicketResponse(tickets));
    }

}
