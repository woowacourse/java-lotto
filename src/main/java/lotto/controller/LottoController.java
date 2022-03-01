package lotto.controller;

import lotto.domain.*;
import lotto.util.StringConverter;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class LottoController {

    private static final String LOTTO_INPUT_DELIMITER = ",";

    public void run() {
        PurchaseAmount purchaseAmount = getPurchaseAmount();
        int ticketCount = purchaseAmount.countTickets();
        OutputView.printTicketCount(ticketCount);

        List<Lotto> lottoTickets = getTickets(ticketCount);
        OutputView.printTickets(lottoTickets);

        WinningLotto winningLotto = getWinningLotto();
        RankBoard rankBoard = new RankBoard(winningLotto, lottoTickets);

        OutputView.printResult(rankBoard, rankBoard.calculateProfitRatio(purchaseAmount.getAmount()));
    }

    private PurchaseAmount getPurchaseAmount() {
        try {
            return new PurchaseAmount(StringConverter.toInt(InputView.getAmount()));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getPurchaseAmount();
        }
    }

    private List<Lotto> getTickets(int ticketCount) {
        LottoMachine lottoMachine = new LottoMachine(ticketCount, 0);
        return lottoMachine.makeLottoTickets(new ArrayList<>());
    }

    private WinningLotto getWinningLotto() {
        Lotto winningNumber = getWinningNumber();
        return makeWinningLotto(winningNumber);
    }

    private Lotto getWinningNumber() {
        try {
            List<Integer> input = StringConverter.toInts(InputView.getWinningNumber(), LOTTO_INPUT_DELIMITER);
            return Lotto.of(input);
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
            return new LottoNumber(StringConverter.toInt(InputView.getBonusNumber()));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getBonusNumber();
        }
    }
}
