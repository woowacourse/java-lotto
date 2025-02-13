package controller;

import controller.dto.LottoDtoMapper;
import controller.dto.LottoRankResponse;
import controller.dto.WinningLottoRequest;
import java.util.List;
import java.util.Map;
import model.LottoRank;
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
        List<LottoTicket> lottoTickets = createLottoTicket();
        WinningLotto winningLotto = createWinningLotto();
        calculateRank(lottoTickets, winningLotto);
    }

    private List<LottoTicket> createLottoTicket() {
        int purchaseAmount = lottoConsoleView.requestPurchaseAmount();
        List<LottoTicket> lottoTickets = lottoStore.purchase(purchaseAmount);

        lottoConsoleView.printPurchaseCount(lottoTickets.size());
        lottoConsoleView.printPurchasedLotto(lottoDtoMapper.toLottoTicketResponse(lottoTickets));

        return lottoTickets;
    }

    private WinningLotto createWinningLotto() {
        WinningLottoRequest winningLottoRequest = lottoConsoleView.requestWinningLotto();
        return lottoDtoMapper.toWinningLotto(winningLottoRequest);
    }

    private void calculateRank(List<LottoTicket> lottoTickets, WinningLotto winningLotto) {
        Map<LottoRank, Integer> rankMatchCounts = lottoStore.calculateRankMatchCount(lottoTickets, winningLotto);
        List<LottoRankResponse> lottoRankResponses = lottoDtoMapper.toLottoRankResponses(rankMatchCounts);
        lottoConsoleView.printLottoRankResults(lottoRankResponses);
    }
}
