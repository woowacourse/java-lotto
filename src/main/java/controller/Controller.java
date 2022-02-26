package controller;

import domain.LottoMachine;
import domain.LottoNumber;
import domain.Ticket;
import domain.WinningNumbers;
import view.InputView;
import view.OutputView;

public class Controller {
    public void run() {
        LottoMachine lotto = getLotto();
        printTickets(lotto);
        WinningNumbers winningNumbers = getWinningNumbers();
        printResult(lotto, winningNumbers);
    }

    private LottoMachine getLotto() {
        try {
            return LottoMachine.from(InputView.requestAmount());
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception.getMessage());
            return getLotto();
        }
    }

    private void printTickets(LottoMachine lotto) {
        OutputView.printTicketCount(lotto.getTicketCount());
        OutputView.printTickets(lotto.getTickets());
    }

    private WinningNumbers getWinningNumbers() {
        try {
            Ticket winTicket = getWinTicket();
            LottoNumber bonusNumber = getBonusNumber();
            return new WinningNumbers(winTicket, bonusNumber);
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception.getMessage());
            return getWinningNumbers();
        }
    }

    private Ticket getWinTicket() {
        try {
            return Ticket.from(InputView.requestWinNumbers());
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception.getMessage());
            return getWinTicket();
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

    private void printResult(LottoMachine lotto, WinningNumbers winningNumbers) {
        OutputView.printResult(lotto.getResult(winningNumbers));
        OutputView.printYield(lotto.getYield(winningNumbers));
    }
}
