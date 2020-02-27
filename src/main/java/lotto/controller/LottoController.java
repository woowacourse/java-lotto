package lotto.controller;

import lotto.domain.*;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    public void play() {
        PurchaseAmount purchaseAmount = InputView.inputPurchaseAmount();
        OutputView.printChangeMoney(purchaseAmount.giveChangeMoney());

        LottoTicketNumber lottoTicketNumber = InputView.inputManualTicketNumber(purchaseAmount);
        LottoStore lottoStore = new LottoStore(lottoTicketNumber);
        LottoTickets lottoTickets = lottoStore.generateLottoTickets(InputView.inputManualLottoTickets(lottoTicketNumber));

        WinningBalls winningBalls = InputView.generateWinningBalls();
        OutputView.printEarningRate(new EarningRate(WinningRank.generateWinningRank(winningBalls, lottoTickets), purchaseAmount));
    }
}