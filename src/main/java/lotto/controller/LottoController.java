package lotto.controller;

import lotto.domain.*;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

public class LottoController {

    public void run() {
        PurchaseAmount purchaseAmount = getPurchaseAmount();
        int ticketCount = purchaseAmount.calcTheNumberOfTicket();
        OutputView.printTicketCount(ticketCount);

        List<LottoNumbers> lottoTickets = getTickets(ticketCount);
        OutputView.printTickets(lottoTickets);

        TotalWinningNumber totalWinningNumber = getTotalNumber();
        RankBoard rankBoard = new RankBoard(totalWinningNumber, lottoTickets);

        OutputView.printResult(rankBoard, rankBoard.calcProfitRatio(purchaseAmount.getAmount()));
    }

    private PurchaseAmount getPurchaseAmount() {
        try {
            return new PurchaseAmount(InputView.getAmount());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getPurchaseAmount();
        }
    }

    private List<LottoNumbers> getTickets(int ticketCount) {
        LottoMachine lottoMachine = new LottoMachine();
        return lottoMachine.makeLottoTickets(ticketCount);
    }

    private WinningNumber getWinningNumber() {
        try {
            return new WinningNumber(InputView.getWinningNumber());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getWinningNumber();
        }
    }

    private LottoNumber getBonusNumber() {
        try {
            return new LottoNumber(InputView.getBonusNumber());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getBonusNumber();
        }
    }

    private TotalWinningNumber getTotalNumber() {
        WinningNumber winningNumber = getWinningNumber();
        return makeTotalNumber(winningNumber);
    }

    private TotalWinningNumber makeTotalNumber(WinningNumber winningNumber) {
        LottoNumber bonusNumber = getBonusNumber();
        try {
            return new TotalWinningNumber(winningNumber, bonusNumber);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return makeTotalNumber(winningNumber);
        }
    }
}
