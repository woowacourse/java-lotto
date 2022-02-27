package lotto.controller;

import lotto.domain.*;
import lotto.util.IntsToLottoConverter;
import lotto.util.StringToIntConverter;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

public class LottoController {

    public void run() {
        PurchaseAmount purchaseAmount = getPurchaseAmount();
        int ticketCount = purchaseAmount.calculateTheNumberOfTickets();
        OutputView.printTicketCount(ticketCount);

        List<Lotto> lottoTickets = getTickets(ticketCount);
        OutputView.printTickets(lottoTickets);

        WinningLotto winningLotto = getWinningLotto();
        RankBoard rankBoard = new RankBoard(winningLotto, lottoTickets);

        OutputView.printResult(rankBoard, rankBoard.calculateProfitRatio(purchaseAmount.getAmount()));
    }

    private PurchaseAmount getPurchaseAmount() {
        try {
            return new PurchaseAmount(StringToIntConverter.toInt(InputView.getAmount()));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getPurchaseAmount();
        }
    }

    private List<Lotto> getTickets(int ticketCount) {
        LottoMachine lottoMachine = new LottoMachine();
        return lottoMachine.makeLottoTickets(ticketCount);
    }

    private WinningLotto getWinningLotto() {
        Lotto winningNumber = getWinningNumber();
        return makeWinningLotto(winningNumber);
    }

    private Lotto getWinningNumber() {
        try {
            List<Integer> input = StringToIntConverter.toInts(InputView.getWinningNumber());
            return IntsToLottoConverter.convert(input);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getWinningNumber();
        }
    }

    private WinningLotto makeWinningLotto(Lotto winningNumber) {
        LottoNumber bonusNumber = getBonusNumber();
        try {
            return new WinningLotto(winningNumber, bonusNumber);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return makeWinningLotto(winningNumber);
        }
    }

    private LottoNumber getBonusNumber() {
        try {
            return new LottoNumber(StringToIntConverter.toInt(InputView.getBonusNumber()));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getBonusNumber();
        }
    }
}
