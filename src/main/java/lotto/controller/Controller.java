package lotto.controller;

import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.domain.Ticket;
import lotto.domain.WinningNumbers;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Controller {
    public void run() {
        Lotto lotto = requestLotto();
        OutputView.printTickets(lotto.getTicketCount(), lotto.getTickets());
        WinningNumbers winningNumbers = requestWinningNumbers();
        OutputView.printResult(lotto.getResult(winningNumbers), lotto.getYield(winningNumbers));
    }

    private Lotto requestLotto() {
        try {
            return new Lotto(InputView.requestAmount());
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception.getMessage());
            return requestLotto();
        }
    }

    private WinningNumbers requestWinningNumbers() {
        Ticket winTicket = requestWinNumbers();
        LottoNumber bonusNumber = requestBonusNumber();
        try {
            return new WinningNumbers(winTicket, bonusNumber);
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception.getMessage());
            return requestWinningNumbers();
        }
    }

    private Ticket requestWinNumbers() {
        try {
            return Ticket.of(InputView.requestWinNumbers());
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
