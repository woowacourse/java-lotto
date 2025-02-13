package controller;

import controller.dto.LottoDtoMapper;
import controller.dto.WinningLottoRequest;
import java.util.List;
import model.LottoRankResult;
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
        List<LottoTicket> lottoTickets = createLottoTickets();
        WinningLotto winningLotto = createWinningLotto();

        LottoRankResult lottoRankResult = calculateRank(lottoTickets, winningLotto);
        calculateProfitRate(lottoTickets.size(), lottoRankResult);
    }

    private List<LottoTicket> createLottoTickets() {
        int paidAmount = lottoConsoleView.readPaidAmount();
        List<LottoTicket> lottoTickets = lottoStore.purchase(paidAmount);

        lottoConsoleView.printPurchasedTicketAmount(lottoTickets.size());
        lottoConsoleView.printPurchasedLotto(lottoDtoMapper.toLottoTicketResponse(lottoTickets));

        return lottoTickets;
    }

    private WinningLotto createWinningLotto() {
        WinningLottoRequest winningLottoRequest = lottoConsoleView.readWinningLotto();
        return lottoDtoMapper.toWinningLotto(winningLottoRequest);
    }

    private LottoRankResult calculateRank(List<LottoTicket> lottoTickets, WinningLotto winningLotto) {
        LottoRankResult lottoRankResult = lottoStore.calculateRankMatchCount(lottoTickets, winningLotto);
        lottoConsoleView.printLottoRankResults(lottoDtoMapper.toLottoRankResponses(lottoRankResult));
        return lottoRankResult;
    }

    private void calculateProfitRate(int lottoTicketCount, LottoRankResult lottoRankResult) {
        double profitRate = lottoStore.calculateProfitRate(lottoTicketCount, lottoRankResult);
        lottoConsoleView.printProfitRate(profitRate);
    }
}
