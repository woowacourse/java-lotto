package lotto.controller;

import lotto.Exception.DuplicationException;
import lotto.Exception.NumberOutOfRangeException;
import lotto.domain.*;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;
import java.util.Set;

public class LottoController {
    private PurchaseAmount purchaseAmount;

    public void play() {
        startInputPurchaseAmount();
        LottoStore lottoStore = new LottoStore(purchaseAmount);
        LottoTickets lottoTickets = lottoStore.getLottoTickets();
        WinningBalls winningBalls = generateWinningBalls();
        List<WinningRank> winningRanks = WinningRank.generateWinningRank(winningBalls, lottoTickets);
        OutputView.printEarningRate(new EarningRate(winningRanks, purchaseAmount));
    }

    private void startInputPurchaseAmount() {
        purchaseAmount = InputView.inputPurchaseAmount();
        OutputView.printLottePieces(purchaseAmount.giveLottoTicketNumber());
        OutputView.printChangeMoney(purchaseAmount.giveChangeMoney());
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
