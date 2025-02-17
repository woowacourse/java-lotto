package controller;

import controller.dto.LottoRankResponse;
import controller.dto.LottoRankResultsResponse;
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

    public void handleLottoApplication() {
        List<LottoTicketResponse> lottoTicketResponses = purchaseLottoTicket();
        WinningLottoRequest winningLottoRequest = createWinningLotto();
        LottoRankResultsResponse lottoRankResultsResponse = countAllLottoRanks(lottoTicketResponses, winningLottoRequest);
        calculateProfitRate(lottoTicketResponses.size(), lottoRankResultsResponse);
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
        List<Integer> winningNumbers = lottoConsoleView.requestWinningNumbers();
        int bonusNumber = lottoConsoleView.requestBonusNumber();
        return new WinningLottoRequest(winningNumbers, bonusNumber);
    }

    private LottoRankResultsResponse countAllLottoRanks(
            List<LottoTicketResponse> lottoTicketResponses,
            WinningLottoRequest winningLottoRequest
    ) {
        LottoRankResultsResponse lottoRankResultsResponse = lottoStore.countAllLottoRanks(lottoTicketResponses, winningLottoRequest);

        List<LottoRankResponse> lottoRankResponses = lottoStore.getLottoRankResults(lottoRankResultsResponse);
        lottoConsoleView.printLottoRankResults(lottoRankResponses);

        return lottoRankResultsResponse;
    }

    private void calculateProfitRate(int lottoTicketCount, LottoRankResultsResponse lottoRankResultsResponse) {
        int purchaseAmount = lottoStore.calculatePurchaseAmount(lottoTicketCount);
        double profitRate = lottoStore.calculateProfitRate(purchaseAmount, lottoRankResultsResponse);
        lottoConsoleView.printProfitRate(profitRate);
    }
}
