package lotto.controller;

import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.domain.Ticket;
import lotto.domain.WinningNumbers;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Controller {
    public void run() {
        Lotto lotto = getLotto();
        printTickets(lotto);
        WinningNumbers winningNumbers = getWinningNumbers();
        printResult(lotto, winningNumbers);
    }

    private void printResult(Lotto lotto, WinningNumbers winningNumbers) {
        OutputView.printResult(lotto.getResult(winningNumbers));
        OutputView.printYield(lotto.getYield(winningNumbers));
    }

    private WinningNumbers getWinningNumbers() {
        Ticket winTicket = getWinNumbers();
        LottoNumber bonusNumber = getBonusNumber();
        try {
            return new WinningNumbers(winTicket, bonusNumber);
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception.getMessage());
            return getWinningNumbers();
        }
    }

    private LottoNumber getBonusNumber() {
        try {
            return new LottoNumber(InputView.requestBonusNumber());
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception.getMessage());
            return getBonusNumber();
        }
    }

    private Ticket getWinNumbers() {
        try {
            return Ticket.of(InputView.requestWinNumbers());
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception.getMessage());
            return getWinNumbers();
        }
    }

    private void printTickets(Lotto lotto) {
        OutputView.printTicketCount(lotto.getTicketCount());
        OutputView.printTickets(lotto.getTickets());
    }

    private Lotto getLotto() {
        try {
            return new Lotto(InputView.requestAmount());
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception.getMessage());
            return getLotto();
        }
    }
}
