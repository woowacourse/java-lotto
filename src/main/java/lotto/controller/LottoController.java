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
        Result result = getResult(purchaseAmount, lottoTickets, totalWinningNumber);
        OutputView.printResult(result);
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

    private Result getResult(PurchaseAmount purchaseAmount, List<LottoNumbers> lottoTickets, TotalWinningNumber totalWinningNumber) {
        Result result = new Result();
        result.countRank(totalWinningNumber, lottoTickets);
        result.calcProfitRatio(purchaseAmount.getAmount());
        return result;
    }
}
