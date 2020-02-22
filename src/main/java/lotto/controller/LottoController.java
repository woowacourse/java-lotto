package lotto.controller;

import lotto.domain.*;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class LottoController {
    private static final int END_INDEX = 6;
    private static final int START_INDEX = 0;

    private PurchaseAmount purchaseAmount;

    public void play() {
        startInputPurchaseAmount();
        generateLottoTickets();
        WinningTicket winningTicket = generateWinningBalls();
        List<WinningRank> winningRanks = generateWinningRank(winningTicket);

        EarningRate earningRate = new EarningRate(winningRanks, purchaseAmount);
        OutputView.printResultAllOfRank(winningRanks, earningRate);
        OutputView.printEarningRate(earningRate);
    }

    private WinningTicket generateWinningBalls() {
        try {
            List<LottoBall> winningBallsInput = InputView.InputWinningTicket();
            int bonusBall = InputView.InputBonusBall();
            return new WinningTicket(winningBallsInput, bonusBall);
        } catch (RuntimeException e) {
            OutputView.printErrorMessage(e.getMessage());
            return generateWinningBalls();
        }
    }

    private void generateLottoTickets() {
        for (int i = 0; i < purchaseAmount.lottoTicket(); i++) {
            LottoBallFactory.shuffle();
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
        List<LottoBall> lottoTicket = LottoBallFactory.getInstance(START_INDEX,END_INDEX);

        return new LottoTicket(lottoTicket);
    }

    private List<WinningRank> generateWinningRank(WinningTicket winningTicket) {
        int correctNumber;
        boolean isBonusNumber;
        List<WinningRank> winningRanks = new ArrayList<>();
        List<LottoTicket> lottoTickets = LottoTickets.getLottoTickets();
        for (LottoTicket lottoTicket : lottoTickets) {
            correctNumber = winningTicket.hitLottoBalls(lottoTicket);
            isBonusNumber = winningTicket.hitBonusBall(lottoTicket);
            winningRanks.add(WinningRank.determineRank(correctNumber, isBonusNumber));
        }
        return winningRanks;
    }
}
