package controller;

import controller.dto.WinningLottoRequest;
import java.util.List;
import model.LottoStore;
import model.PaidAmount;
import model.numbers.LottoNumbers;
import model.numbers.WinningLotto;
import model.rank.LottoRankResult;
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
        List<LottoNumbers> lottoNumbers = createLottoNumbers(paidAmount);
        WinningLotto winningLotto = createWinningLotto();

        getResult(lottoNumbers, winningLotto, paidAmount);
    }

    private List<LottoNumbers> createLottoNumbers(PaidAmount paidAmount) {
        List<LottoNumbers> lottoNumbers = lottoStore.purchase(paidAmount);

        lottoConsoleView.printPurchasedTicketAmount(paidAmount.getUnitCount());
        lottoConsoleView.printPurchasedLotto(lottoDtoMapper.toLottoTicketResponses(lottoNumbers));

        return lottoNumbers;
    }

    private WinningLotto createWinningLotto() {
        WinningLottoRequest winningLottoRequest = lottoConsoleView.readWinningLotto();
        return lottoDtoMapper.toWinningLotto(winningLottoRequest);
    }

    private void getResult(List<LottoNumbers> lottoNumbers, WinningLotto winningLotto, PaidAmount paidAmount) {
        LottoRankResult lottoRankResult = lottoStore.calculateRankMatchCount(lottoNumbers, winningLotto);
        lottoConsoleView.printLottoRankResults(lottoDtoMapper.toLottoRankResponses(lottoRankResult));

        double profitRate = lottoStore.calculateProfitRate(paidAmount, lottoRankResult);
        lottoConsoleView.printProfitRate(profitRate);
    }
}
