package controller;

import controller.dto.LottoDtoMapper;
import controller.dto.WinningLottoRequest;
import java.util.List;
import model.LottoNumbers;
import model.LottoRankResult;
import model.LottoStore;
import model.PaidAmount;
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
        PaidAmount paidAmount = new PaidAmount(lottoConsoleView.readPaidAmount());
        List<LottoNumbers> lottoNumbers = createLottoTickets(paidAmount);
        WinningLotto winningLotto = createWinningLotto();

        LottoRankResult lottoRankResult = calculateRank(lottoNumbers, winningLotto);
        calculateProfitRate(paidAmount, lottoRankResult);
    }

    private List<LottoNumbers> createLottoTickets(PaidAmount paidAmount) {
        List<LottoNumbers> lottoNumbers = lottoStore.purchase(paidAmount);

        lottoConsoleView.printPurchasedTicketAmount(lottoNumbers.size());
        lottoConsoleView.printPurchasedLotto(lottoDtoMapper.toLottoTicketResponses(lottoNumbers));

        return lottoNumbers;
    }

    private WinningLotto createWinningLotto() {
        WinningLottoRequest winningLottoRequest = lottoConsoleView.readWinningLotto();
        return lottoDtoMapper.toWinningLotto(winningLottoRequest);
    }

    private LottoRankResult calculateRank(List<LottoNumbers> lottoNumbers, WinningLotto winningLotto) {
        LottoRankResult lottoRankResult = lottoStore.calculateRankMatchCount(lottoNumbers, winningLotto);
        lottoConsoleView.printLottoRankResults(lottoDtoMapper.toLottoRankResponses(lottoRankResult));
        return lottoRankResult;
    }

    private void calculateProfitRate(PaidAmount paidAmount, LottoRankResult lottoRankResult) {
        double profitRate = lottoStore.calculateProfitRate(paidAmount, lottoRankResult);
        lottoConsoleView.printProfitRate(profitRate);
    }
}
