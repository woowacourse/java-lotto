package lotto.controller;

import lotto.domain.*;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoController {
    private static final int MAX_LOTTO_BALL_COUNT = 6;

    private PurchaseAmount purchaseAmount;

    public void play() {
        startInputPurchaseAmount();
        generateLottoTickets();
        WinningBalls winningBalls = generateWinningBalls();
        List<WinningRank> winningRanks = generateWinningRank(winningBalls);

        EarningRate earningRate = new EarningRate(winningRanks, purchaseAmount);
        OutputView.printRankConstant();
        OutputView.printResultAllOfRank(winningRanks, earningRate);
        OutputView.printEarningRate(earningRate);
    }

    private WinningBalls generateWinningBalls() {
        try {
            List<LottoBall> winningBallsInput = InputView.InputWinningBalls();
            int bonusBall = InputView.InputBonusBall();
            return new WinningBalls(winningBallsInput, bonusBall);
        } catch (RuntimeException e) {
            OutputView.printErrorMessage(e.getMessage());
            return generateWinningBalls();
        }
    }

    private void generateLottoTickets() {
        for (int i = 0; i < purchaseAmount.lottoTicket(); i++) {
            Collections.shuffle(LottoBallFactory.getInstance());
            LottoTickets.insertLottoTicket(generateLottoTicket());
        }
        OutputView.printLottoTicket();
    }

    private void startInputPurchaseAmount() {
        OutputView.printStartGuide();
        purchaseAmount = InputView.inputPurchaseAmount();
        OutputView.printLottePieces(purchaseAmount.lottoTicket());
        OutputView.printChangeMoney(purchaseAmount.giveChangeMoney());
    }

    private LottoTicket generateLottoTicket() {
        List<LottoBall> lottoTicket = new ArrayList<>();
        for (int i = 0; i < MAX_LOTTO_BALL_COUNT; i++) {
            lottoTicket.add(LottoBallFactory.getInstance().get(i));
        }
        return new LottoTicket(lottoTicket);
    }

    private List<WinningRank> generateWinningRank(WinningBalls winningBalls) {
        List<WinningRank> winningRanks = new ArrayList<>();
        for (LottoTicket lottoTicket : LottoTickets.getLottoTickets()) {
            int correctNumber = winningBalls.hitLottoBalls(lottoTicket);
            boolean isBonusNumber = winningBalls.hitBonusBall(lottoTicket);
            winningRanks.add(WinningRank.selectRank(correctNumber, isBonusNumber));
        }
        return winningRanks;
    }
}
