package controller;

import domain.LottoMachine;
import domain.LottoNumber;
import domain.RandomLottoNumbersGenerator;
import domain.Ticket;
import domain.WinningNumbers;
import view.InputView;
import view.OutputView;

public class Controller {
    public void run() {
        LottoMachine lottoMachine = createLotto();
        printTickets(lottoMachine);
        WinningNumbers winningNumbers = getWinningNumbers();
        printResult(lottoMachine, winningNumbers);
    }

    private LottoMachine createLotto() {
        try {
            return LottoMachine.from(InputView.requestAmount(), new RandomLottoNumbersGenerator());
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception.getMessage());
            return createLotto();
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
