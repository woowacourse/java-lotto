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
        LottoMachine lottoMachine = createLottoMachine(purchaseAmount.countTickets());

        List<Lotto> manualLottos = getManualLottos(lottoMachine.getManualTicketCount());
        OutputView.printTicketCount(lottoMachine);

        List<Lotto> lottoTickets = lottoMachine.makeLottoTickets(manualLottos);
        OutputView.printTickets(lottoTickets);

        RankBoard rankBoard = new RankBoard(getWinningLotto(), lottoTickets);
        OutputView.printResult(rankBoard, rankBoard.calculateProfitRatio(purchaseAmount.getAmount()));
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

    private List<Lotto> getManualLottos(int manualTicketCount) {
        try {
            return getLottoNumbers(manualTicketCount);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getManualLottos(manualTicketCount);
        }
    }

    private List<Lotto> getLottoNumbers(int manualTicketCount) {
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
        List<Lotto> manualLottos = new ArrayList<>();
        addLottoNumbers(manualTicketCount, manualLottos);
        return manualLottos;
    }

    private void addLottoNumbers(int manualTicketCount, List<Lotto> manualLottos) {
        for (int i = 0; i < manualTicketCount; i++) {
            manualLottos.add(Lotto.of(StringConverter.toInts(InputView.getLottoNumbers(), LOTTO_INPUT_DELIMITER)));
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
