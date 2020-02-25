package lotto.controller;

import lotto.Exception.DuplicationException;
import lotto.Exception.NumberOutOfRangeException;
import lotto.domain.*;
import lotto.util.InputValidationUtil;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;
import java.util.Set;

public class LottoController {

    public void play() {
        PurchaseAmount purchaseAmount = startInputPurchaseAmount();
        LottoTicketNumber lottoTicketNumber = generateLottoTicketNumber(purchaseAmount);
        LottoStore lottoStore = new LottoStore(purchaseAmount);
        LottoTickets lottoTickets = lottoStore.getLottoTickets();
        WinningBalls winningBalls = generateWinningBalls();
        List<WinningRank> winningRanks = WinningRank.generateWinningRank(winningBalls, lottoTickets);
        OutputView.printEarningRate(new EarningRate(winningRanks, purchaseAmount));
    }

    private LottoTicketNumber generateLottoTicketNumber(PurchaseAmount purchaseAmount) {
        try {
            return new LottoTicketNumber(purchaseAmount.giveLottoTicketNumber(), InputView.inputManualTicketNumber());
        } catch (NumberOutOfRangeException e) {
            OutputView.printErrorMessage(e.getMessage());
            return generateLottoTicketNumber(purchaseAmount);
        }
    }

    private PurchaseAmount startInputPurchaseAmount() {
        PurchaseAmount purchaseAmount = InputView.inputPurchaseAmount();
        OutputView.printLottePieces(purchaseAmount.giveLottoTicketNumber());
        OutputView.printChangeMoney(purchaseAmount.giveChangeMoney());
        return purchaseAmount;
    }

    private WinningBalls generateWinningBalls() {
        try {
            Set<LottoBall> winningBalls = InputView.InputWinningBalls();
            LottoBall bonusBall = InputView.InputBonusBall();
            return new WinningBalls(winningBalls, bonusBall);
        } catch (DuplicationException | NumberOutOfRangeException e) {
            OutputView.printErrorMessage(e.getMessage());
            return generateWinningBalls();
        }
    }
}