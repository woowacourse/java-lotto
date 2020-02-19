package lotto.controller;

import lotto.domain.*;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoController {
    private static PurchaseAmount purchaseAmount;
    private static LottoTickets lottoTickets;

    public static void play() {
        startInputPurchaseAmount();
        for (int i = 0; i < purchaseAmount.lottoTicket(); i++) {
            Collections.shuffle(LottoBallFactory.getInstance());
            lottoTickets = new LottoTickets(generateLottoTicket());
        }
        OutputView.printLottoTicket();

        OutputView.printAnswerWinningBalls();
        List<LottoBall> winningBallsInput = InputView.InputWinningBalls();
        OutputView.printAnswerBonusBall();
        int bonusBall = InputView.InputBonusBall();

        WinningBalls winningBalls = new WinningBalls(winningBallsInput, bonusBall);

    }

    private static void startInputPurchaseAmount() {
            OutputView.printStartGuide();
            purchaseAmount = InputView.inputPurchaseAmount();
            OutputView.printLottePieces(purchaseAmount.lottoTicket());
            OutputView.printChangeMoney(purchaseAmount.giveChangeMoney());
    }

    public static LottoTicket generateLottoTicket() {
        List<LottoBall> lottoTicket = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            lottoTicket.add(LottoBallFactory.getInstance().get(i));
        }
        return new LottoTicket(lottoTicket);
    }
}
