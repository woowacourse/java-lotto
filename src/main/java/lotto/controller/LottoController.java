package lotto.controller;

import lotto.Exception.DuplicationException;
import lotto.Exception.NumberOutOfRangeException;
import lotto.domain.*;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class LottoController {

    public void play() {
        LottoStore lottoStore = new LottoStore();
        PurchaseAmount purchaseAmount = lottoStore.getPurchaseAmount();
        LottoTickets lottoTickets = lottoStore.getLottoTickets();
        WinningBalls winningBalls = generateWinningBalls();
        List<WinningRank> winningRanks = generateWinningRank(winningBalls, lottoTickets);
        OutputView.printEarningRate(new EarningRate(winningRanks, purchaseAmount));
    }

    private WinningBalls generateWinningBalls() {
        try {
            List<LottoBall> winningBallsInput = InputView.InputWinningBalls();
            int bonusBall = InputView.InputBonusBall();
            return new WinningBalls(winningBallsInput, bonusBall);
        } catch (DuplicationException | NumberOutOfRangeException e) {
            OutputView.printErrorMessage(e.getMessage());
            return generateWinningBalls();
        }
    }

    private List<WinningRank> generateWinningRank(WinningBalls winningBalls, LottoTickets lottoTickets) {
        List<WinningRank> winningRanks = new ArrayList<>();
        for (LottoTicket lottoTicket : lottoTickets.getLottoTickets()) {
            int correctNumber = winningBalls.hitLottoBalls(lottoTicket);
            boolean isBonusNumber = winningBalls.hitBonusBall(lottoTicket);
            winningRanks.add(WinningRank.selectRank(correctNumber, isBonusNumber));
        }
        return winningRanks;
    }
}
