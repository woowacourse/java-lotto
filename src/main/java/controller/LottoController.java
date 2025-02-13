package controller;

import controller.dto.LottoDtoMapper;
import controller.dto.WinningLottoRequest;
import java.util.List;
import model.LottoStore;
import model.LottoTicket;
import model.WinningLotto;
import view.LottoConsoleView;

public class LottoController {

    private final LottoConsoleView lottoConsoleView;
    private final LottoStore lottoStore;
    private final LottoDtoMapper lottoDtoMapper;

    public LottoController(LottoConsoleView lottoConsoleView, LottoStore lottoStore, LottoDtoMapper lottoDtoMapper) {
        this.lottoConsoleView = lottoConsoleView;
        this.lottoStore = lottoStore;
        this.lottoDtoMapper = lottoDtoMapper;
    }

    public void run() {
        List<LottoTicket> tickets = createLottoTicket();
        WinningLotto winningLotto = createWinningLotto();


    }

    private List<LottoTicket> createLottoTicket() {
        int purchaseAmount = lottoConsoleView.requestPurchaseAmount();
        List<LottoTicket> tickets = lottoStore.purchase(purchaseAmount);

        lottoConsoleView.printPurchaseCount(tickets.size());
        lottoConsoleView.printPurchasedLotto(lottoDtoMapper.toLottoTicketResponse(tickets));

        return tickets;
    }

    private WinningLotto createWinningLotto() {
        WinningLottoRequest winningLottoRequest = lottoConsoleView.requestWinningLotto();
        return lottoDtoMapper.toWinningLotto(winningLottoRequest);
    }
}
