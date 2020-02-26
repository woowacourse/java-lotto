package lotto.controller;

import lotto.Exception.DuplicationException;
import lotto.Exception.NumberOutOfRangeException;
import lotto.domain.*;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class LottoController {

    public void play() {
        PurchaseAmount purchaseAmount = generatePurchaseAmount();
        LottoTicketNumber lottoTicketNumber = InputView.inputManualTicketNumber(purchaseAmount);
        LottoStore lottoStore = new LottoStore(lottoTicketNumber);
        LottoTickets lottoTickets = generateLottoTickets(lottoTicketNumber.getManualLottoTicketNumber(), lottoStore);
        WinningBalls winningBalls = generateWinningBalls();
        List<WinningRank> winningRanks = WinningRank.generateWinningRank(winningBalls, lottoTickets);
        OutputView.printEarningRate(new EarningRate(winningRanks, purchaseAmount));
    }

    private PurchaseAmount generatePurchaseAmount() {
        PurchaseAmount purchaseAmount = InputView.inputPurchaseAmount();
        OutputView.printChangeMoney(purchaseAmount.giveChangeMoney());
        return purchaseAmount;
    }

    private LottoTickets generateLottoTickets(int manualLottoTicketNumber, LottoStore lottoStore){
        List<String> manualLottoBallsInputs = new ArrayList<>();
        for (int i = 0; i < manualLottoTicketNumber; i++){
            manualLottoBallsInputs.add(InputView.InputWinningBallsAndManualLottoBalls());
        }
        return lottoStore.generateLottoTickets(manualLottoBallsInputs);
    }

    private WinningBalls generateWinningBalls() {
        try {
            OutputView.printAnswerWinningBalls();
            Set<LottoBall> winningBalls = LottoBalls.generateLottoBalls(InputView.InputWinningBallsAndManualLottoBalls());
            LottoBall bonusBall = InputView.InputBonusBall();
            return new WinningBalls(winningBalls, bonusBall);
        } catch (DuplicationException | NumberOutOfRangeException e) {
            OutputView.printErrorMessage(e.getMessage());
            return generateWinningBalls();
        }
    }
}