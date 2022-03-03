package lotto.controller;

import lotto.domain.*;
import lotto.util.StringConverter;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;
import java.util.stream.Collectors;

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
        List<String> lottoNumbers = InputView.getLottoNumbers(manualTicketCount);
        return lottoNumbers.stream()
                .map(numbers -> Lotto.of(StringConverter.toInts(numbers, LOTTO_INPUT_DELIMITER)))
                .collect(Collectors.toList());
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
