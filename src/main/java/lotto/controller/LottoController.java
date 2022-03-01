package lotto.controller;

import java.util.ArrayList;
import lotto.domain.*;
import lotto.util.StringConverter;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

public class LottoController {

    public void run() {
        PurchaseAmount purchaseAmount = getPurchaseAmount();
        LottoGame lottoGame = new LottoGame(purchaseAmount, StringConverter.toInt(InputView.getManualLottoCount()));

        InputView.printGetManualLottosMessage();

        List<List<Integer>> inputLottos = new ArrayList<>();
        for (int i = 0; i < lottoGame.getManualLottoCount(); i++) {
            inputLottos.add(StringConverter.toInts(InputView.getManualLotto()));
        }
        lottoGame.makeManualLottos(inputLottos);
        lottoGame.makeAutoLottos();

        OutputView.printLottoCount(lottoGame.getManualLottoCount(), lottoGame.getAutoLottoCount());

        OutputView.printLottos(lottoGame.getLottos());

        WinningLotto winningLotto = getWinningLotto();

//        int ticketCount = purchaseAmount.calculateTheNumberOfTicket();
//        OutputView.printTicketCount(ticketCount);
//
//        List<Lotto> lottoTickets = getLottoTickets(ticketCount);
//        OutputView.printLottos(lottoTickets);
//
//        WinningLotto winningLotto = getWinningLotto();
//        RankBoard rankBoard = new RankBoard(winningLotto, lottoTickets);
//
//        OutputView.printResult(rankBoard, rankBoard.calculateProfitRatio(purchaseAmount.getAmount()));
    }

    private PurchaseAmount getPurchaseAmount() {
        try {
            return new PurchaseAmount(StringConverter.toInt(InputView.getAmount()));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getPurchaseAmount();
        }
    }

    private WinningLotto getWinningLotto() {
        try {
            List<Integer> winningNumbers = StringConverter.toInts(InputView.getWinningNumbers());
            int bonusNumber = StringConverter.toInt(InputView.getBonusNumber());
            return new WinningLotto(winningNumbers, bonusNumber);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getWinningLotto();
        }
    }
}
