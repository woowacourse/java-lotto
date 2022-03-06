package lotto.controller;

import java.util.List;

import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.domain.Ticket;
import lotto.domain.WinTicket;
import lotto.utils.RandomLottoNumbersGenerator;
import lotto.utils.StringUtil;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Controller {
    private static final String SPLIT_DELIMITER = ", ";

    public void run() {
        Lotto lotto = createLotto();

        int manualTicketCount = requestManualTicketCount(lotto);
        createManualTickets(lotto, manualTicketCount);
        createAutoTickets(lotto);

        OutputView.printTickets(lotto.getManualTicketCount(), lotto.getAutoTicketCount(), lotto.getTickets());
        WinTicket winTicket = createWinTicket();
        OutputView.printResult(lotto.getResult(winTicket), lotto.getYield(winTicket));
    }

    private Lotto createLotto() {
        try {
            return new Lotto(requestAmount());
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception.getMessage());
            return createLotto();
        }
    }

    private int requestAmount() {
        return InputView.requestAmount();
    }

    private int requestManualTicketCount(Lotto lotto) {
        int manualTicketCount = InputView.requestManualTicketCount();
        try {
            lotto.validateTicketCount(manualTicketCount);
            return manualTicketCount;
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return requestManualTicketCount(lotto);
        }
    }

    private void createManualTickets(Lotto lotto, int manualTicketCount) {
        InputView.requestManualTicket();
        for (int i = 0; i < manualTicketCount; i++) {
            createManualTicket(lotto);
        }
    }

    private void createManualTicket(Lotto lotto) {
        try {
            List<Integer> integers = StringUtil.splitToIntegers(InputView.requestNumbers(), SPLIT_DELIMITER);
            lotto.addTicket(Ticket.createByIntegers(integers, false));
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            createManualTicket(lotto);
        }
    }

    private void createAutoTickets(Lotto lotto) {
        int numberOfPurchasableCount = lotto.findCountOfPurchasable();
        for (int i = 0; i < numberOfPurchasableCount; i++) {
            lotto.addTicket(Ticket.createByImplementation(new RandomLottoNumbersGenerator(), true));
        }
    }

    private WinTicket createWinTicket() {
        Ticket winTicket = requestWinNumbers();
        LottoNumber bonusNumber = requestBonusNumber();
        try {
            return new WinTicket(winTicket, bonusNumber);
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception.getMessage());
            return createWinTicket();
        }
    }

    private Ticket requestWinNumbers() {
        try {
            List<Integer> integers = StringUtil.splitToIntegers(InputView.requestWinNumbers(), SPLIT_DELIMITER);
            return Ticket.createByIntegers(integers, false);
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception.getMessage());
            return requestWinNumbers();
        }
    }

    private LottoNumber requestBonusNumber() {
        try {
            return new LottoNumber(InputView.requestBonusNumber());
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception.getMessage());
            return requestBonusNumber();
        }
    }
}
