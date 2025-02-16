package controller;

import controller.dto.LottoRankResponse;
import controller.dto.LottoRankResultResponse;
import controller.dto.LottoTicketResponse;
import controller.dto.WinningLottoRequest;
import java.util.List;
import model.LottoStore;
import view.LottoConsoleView;

public class LottoController {

    private final LottoConsoleView lottoConsoleView;
    private final LottoStore lottoStore;

    public LottoController(LottoConsoleView lottoConsoleView, LottoStore lottoStore) {
        this.lottoConsoleView = lottoConsoleView;
        this.lottoStore = lottoStore;
    }

    public void run() {
        List<LottoTicketResponse> lottoTicketResponses = purchaseLottoTicket();
        WinningLottoRequest winningLottoRequest = createWinningLotto();
        LottoRankResultResponse lottoRankResultResponse = calculateRank(lottoTicketResponses, winningLottoRequest);
        calculateProfitRate(lottoTicketResponses.size(), lottoRankResultResponse);
    }

    private List<LottoTicketResponse> purchaseLottoTicket() {
        int purchaseAmount = lottoConsoleView.requestPurchaseAmount();
        int purchaseCount = lottoStore.calculatePurchaseCount(purchaseAmount);
        List<LottoTicketResponse> lottoTicketResponses = lottoStore.createLottoTickets(purchaseCount);

        lottoConsoleView.printPurchaseCount(purchaseCount);
        lottoConsoleView.printPurchasedLotto(lottoTicketResponses);

        return lottoTicketResponses;
    }

    private WinningLottoRequest createWinningLotto() {
        WinningLottoRequest winningLottoRequest = lottoConsoleView.requestWinningLotto();
        return winningLottoRequest;
    }

    private LottoRankResultResponse calculateRank(
            List<LottoTicketResponse> lottoTicketResponses,
            WinningLottoRequest winningLottoRequest
    ) {
        LottoRankResultResponse lottoRankResultResponse = lottoStore.calculateRankMatchCount(lottoTicketResponses, winningLottoRequest);

        List<LottoRankResponse> lottoRankResponses = lottoStore.getLottoRankResults(lottoRankResultResponse);
        lottoConsoleView.printLottoRankResults(lottoRankResponses);

        return lottoRankResultResponse;
    }

    private void calculateProfitRate(int lottoTicketCount, LottoRankResultResponse lottoRankResultResponse) {
        double profitRate = lottoStore.calculateProfitRate(lottoTicketCount, lottoRankResultResponse);
        lottoConsoleView.printProfitRate(profitRate);
    }
}
