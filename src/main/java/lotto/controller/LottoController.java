package lotto.controller;

import lotto.domain.*;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoController {
    public static final int MAX_LOTTO_BALL_COUNT = 6;
    private static PurchaseAmount purchaseAmount;

    public static void play() {
        startInputPurchaseAmount();
        generateLottoTickets();

        WinningBalls winningBalls = generateWinningBalls();
        List<WinningRank> winningRanks = generateWinningRAnd(winningBalls);
        generateEarningRate(winningRanks);
    }

    private static void generateEarningRate(List<WinningRank> winningRanks) {
        EarningRate earningRate = new EarningRate();
        for (WinningRank winningRank1 : WinningRank.values()) {
            int count = (int) winningRanks.stream()
                    .filter(winningRank -> winningRank == winningRank1)
                    .count();
            OutputView.printWinningResult(winningRank1, count);
            earningRate.sumWinningMoney(winningRank1.getWinningMoney() * count);
        }
        earningRate.calculateEarningRate(purchaseAmount);
        OutputView.printEarningRate(earningRate);
    }

    private static WinningBalls generateWinningBalls() {
        OutputView.printAnswerWinningBalls();
        List<LottoBall> winningBallsInput = InputView.InputWinningBalls();
        OutputView.printAnswerBonusBall();
        int bonusBall = InputView.InputBonusBall();

        return new WinningBalls(winningBallsInput, bonusBall);
    }

    private static void generateLottoTickets() {
        for (int i = 0; i < purchaseAmount.lottoTicket(); i++) {
            Collections.shuffle(LottoBallFactory.getInstance());
            new LottoTickets(generateLottoTicket());
        }
        OutputView.printLottoTicket();
    }

    private static void startInputPurchaseAmount() {
        OutputView.printStartGuide();
        purchaseAmount = InputView.inputPurchaseAmount();
        OutputView.printLottePieces(purchaseAmount.lottoTicket());
        OutputView.printChangeMoney(purchaseAmount.giveChangeMoney());
    }

    private static LottoTicket generateLottoTicket() {
        List<LottoBall> lottoTicket = new ArrayList<>();
        for (int i = 0; i < MAX_LOTTO_BALL_COUNT; i++) {
            lottoTicket.add(LottoBallFactory.getInstance().get(i));
        }
        return new LottoTicket(lottoTicket);
    }

    private static List<WinningRank> generateWinningRAnd(WinningBalls winningBalls) {
        List<WinningRank> winningRanks = new ArrayList<>();
        for (LottoTicket lottoTicket : LottoTickets.getLottoTickets()) {
            int correctNumber = winningBalls.hitLottoBalls(lottoTicket);
            boolean isBonusNumber = winningBalls.hitBonusBall(lottoTicket);
            winningRanks.add(WinningRank.selectRank(correctNumber, isBonusNumber));
        }
        return winningRanks;
    }
}
