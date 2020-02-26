package lotto.controller;

import lotto.domain.*;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class LottoController {

    private Money money;
    private TicketingCount ticketingTotalCount;
    private TicketingCount manualTicketingCount;

    public void play() {
        startInputMoney();
        generateManualLottoTickets();
        generateLottoTickets();

        WinningTicket winningTicket = generateWinningBalls();
        List<WinningRank> winningRanks = generateWinningRank(winningTicket);

        EarningRate earningRate = new EarningRate(winningRanks, money);
        OutputView.printResultAllOfRank(winningRanks, earningRate);
        OutputView.printEarningRate(earningRate);
    }

    private void startInputMoney() {
        OutputView.printStartGuide();
        money = InputView.inputPurchaseAmount();
        generateTicketCount();
    }

    private void generateTicketCount() {
        try {
            ticketingTotalCount = new TicketingCount(money.lottoTicketCount());
        } catch (RuntimeException e) {
            OutputView.printErrorMessage(e.getMessage());
            generateTicketCount();
        }
    }

    private void generateManualLottoTickets() {
        OutputView.printManualLottoTicketingCount();
        manualTicketingCount = InputView.inputManualTicketingCount(ticketingTotalCount);

        for (int i = 0; i < manualTicketingCount.getTicketingCount(); i++) {
            LottoTickets.insertLottoTicket(new LottoTicket(InputView.InputManualLottoTicket()));
        }
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
        ticketingTotalCount.calculateCount(manualTicketingCount);

        for (int i = 0; i < ticketingTotalCount.getTicketingCount(); i++) {
            LottoBallFactory.shuffle();
            LottoTickets.insertLottoTicket(generateLottoTicket());
        }
        OutputView.printLottoTicket(ticketingTotalCount, manualTicketingCount);
        OutputView.printChangeMoney(money.giveChangeMoney());
    }

    private LottoTicket generateLottoTicket() {
        List<LottoBall> lottoTicket = LottoBallFactory.generateLottoTicket();

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
