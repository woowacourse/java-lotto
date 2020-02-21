package lotto.controller;

import lotto.domain.*;
import lotto.view.OutputView;

import java.util.List;

public class LottoController {
    public void play() {
        LottoStore lottoStore = new LottoStore();
        PurchaseAmount purchaseAmount = lottoStore.getPurchaseAmount();
        LottoTickets lottoTickets = lottoStore.getLottoTickets();
        WinningBalls winningBalls = new WinningBalls();
        List<WinningRank> winningRanks = WinningRank.generateWinningRank(winningBalls, lottoTickets);
        OutputView.printEarningRate(new EarningRate(winningRanks, purchaseAmount));
    }
}
