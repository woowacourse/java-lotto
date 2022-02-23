package lotto.controller;

import lotto.domain.*;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;
import java.util.Set;

public class LottoController {

    public void run() {
        PurchaseAmount purchaseAmount = getPurchaseAmount();
        int ticketCount = purchaseAmount.calcTheNumberOfTicket();
        OutputView.printTicketCount(ticketCount);

        List<Set<LottoNumber>> lottoTickets = getTickets(ticketCount);
        OutputView.printTickets(lottoTickets);

        TotalNumber totalNumber = getTotalNumber();
        Result result = getResult(purchaseAmount, lottoTickets, totalNumber);
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

    private List<Set<LottoNumber>> getTickets(int ticketCount) {
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

    private TotalNumber getTotalNumber() {
        WinningNumber winningNumber = getWinningNumber();
        return makeTotalNumber(winningNumber);
    }

    private TotalNumber makeTotalNumber(WinningNumber winningNumber) {
        LottoNumber bonusNumber = getBonusNumber();
        try {
            return new TotalNumber(winningNumber, bonusNumber);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return makeTotalNumber(winningNumber);
        }
    }

    private Result getResult(PurchaseAmount purchaseAmount, List<Set<LottoNumber>> lottoTickets, TotalNumber totalNumber) {
        Result result = new Result();
        result.countRank(totalNumber, lottoTickets);
        result.calcProfitRatio(purchaseAmount.getAmount());
        return result;
    }
}
