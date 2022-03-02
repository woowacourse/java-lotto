package lotto.controller;

import java.util.Set;

import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.domain.Ticket;
import lotto.domain.WinTicket;
import lotto.utils.RandomLottoNumbersGenerator;
import lotto.utils.WinTicketGenerator;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Controller {
    public void run() {
        Lotto lotto = requestAmountForLotto();
        OutputView.printTickets(lotto.getTicketCount(), lotto.getTickets());
        WinTicket winTicket = createWinTicket();
        OutputView.printResult(lotto.getResult(winTicket), lotto.getYield(winTicket));
    }

    private Lotto requestAmountForLotto() {
        try {
            return new Lotto(InputView.requestAmount(), new RandomLottoNumbersGenerator());
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception.getMessage());
            return requestAmountForLotto();
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
            Set<LottoNumber> winLottoNumbers = WinTicketGenerator.generate(InputView.requestWinNumbers());
            return new Ticket(winLottoNumbers);
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
