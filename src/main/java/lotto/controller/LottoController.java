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

        LottoMachine lottoMachine = createLottoMachine(ticketCount);
        int manualTicketCount = lottoMachine.getManualTicketCount();
        List<Lotto> manualLottos = getManualLottos(manualTicketCount);
        OutputView.printTicketCount(lottoMachine);
        List<Lotto> lottoTickets = lottoMachine.makeLottoTickets(manualLottos);
        OutputView.printTickets(lottoTickets);

        WinningLotto winningLotto = getWinningLotto();
        RankBoard rankBoard = new RankBoard(winningLotto, lottoTickets);

        OutputView.printResult(rankBoard, rankBoard.calculateProfitRatio(purchaseAmount.getAmount()));
    }

    private List<Lotto> getManualLottos(int manualTicketCount) {
        try {
            System.out.println("수동으로 구매할 번호를 입력해 주세요.");
            List<Lotto> manualLottos = new ArrayList<>();
            for (int i = 0; i < manualTicketCount; i++) {
                manualLottos.add(Lotto.of(StringConverter.toInts(InputView.getLottoNumbers(), LOTTO_INPUT_DELIMITER)));
            }
            return manualLottos;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getManualLottos(manualTicketCount);
        }
    }

    private LottoMachine createLottoMachine(int ticketCount) {
        try {
            int manualTicketCount = StringConverter.toInt(InputView.getManualTicketCount());
            return new LottoMachine(ticketCount, manualTicketCount);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return createLottoMachine(ticketCount);
        }
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
