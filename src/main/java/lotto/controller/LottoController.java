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
        WinningBalls winningBalls;

        startInputPurchaseAmount();
        generateLottoTickets();

        winningBalls = generateWinningBalls();
    }

    private static WinningBalls generateWinningBalls() {
        OutputView.printAnswerWinningBalls();
        List<LottoBall> winningBallsInput = InputView.InputWinningBalls();
        OutputView.printAnswerBonusBall();
        int bonusBall = InputView.InputBonusBall();

        return  new WinningBalls(winningBallsInput, bonusBall);
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

    public static LottoTicket generateLottoTicket() {
        List<LottoBall> lottoTicket = new ArrayList<>();
        for (int i = 0; i < MAX_LOTTO_BALL_COUNT; i++) {
            lottoTicket.add(LottoBallFactory.getInstance().get(i));
        }
        return new LottoTicket(lottoTicket);
    }
}
