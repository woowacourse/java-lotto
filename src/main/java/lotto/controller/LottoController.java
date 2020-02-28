package lotto.controller;

import lotto.domain.*;
import lotto.domain.LottoTicketNumber.AutomaticLottoTicketNumber;
import lotto.domain.LottoTicketNumber.ManualLottoTicketNumber;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    public void play() {
        PurchaseAmount purchaseAmount = InputView.inputPurchaseAmount();
        ManualLottoTicketNumber manualLottoTicketNumber = InputView.inputManualTicketNumber(purchaseAmount);
        AutomaticLottoTicketNumber automaticLottoTicketNumber = new AutomaticLottoTicketNumber(
                manualLottoTicketNumber.getLottoTicketNumber(), purchaseAmount.giveTotalLottoTicketNumber());

        LottoStore lottoStore = new LottoStore(manualLottoTicketNumber, automaticLottoTicketNumber);
        LottoTickets lottoTickets = lottoStore.generateLottoTickets(InputView.inputManualLottoTickets(manualLottoTicketNumber));

        WinningBalls winningBalls = InputView.generateWinningBalls();
        OutputView.printEarningRate(new EarningRate(WinningRank.generateWinningRank(winningBalls, lottoTickets), purchaseAmount));
    }
}