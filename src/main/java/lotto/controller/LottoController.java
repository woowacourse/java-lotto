package lotto.controller;

import lotto.domain.Accountant;
import lotto.domain.LottoNumber;
import lotto.domain.LottoResult;
import lotto.domain.LottoTicket;
import lotto.domain.LottoTickets;
import lotto.domain.Money;
import lotto.exceptions.InvalidLottoNumberException;
import lotto.exceptions.InvalidLottoTicketException;
import lotto.exceptions.InvalidMoneyException;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    public static void run() {
        LottoResult lottoResult = LottoResult.create();
        Money money = getMoney();
        LottoTickets tickets = getLottoTickets(money);
        LottoTicket winningLotto = getLottoTicket();
        LottoNumber bonus = getBonusNumber(winningLotto);
        OutputView.prizeStatistics(lottoResult.match(tickets, winningLotto, bonus));
        OutputView.profitRate(Accountant.calculate(money, lottoResult));
    }

    private static LottoNumber getBonusNumber(LottoTicket winningLotto) {
        try {
            OutputView.inputBonusNumberInstruction();
            LottoNumber bonusNumber = LottoNumber.find(InputView.getInput());
            winningLotto.validateBonusNumber(bonusNumber);
            return bonusNumber;
        } catch (InvalidLottoNumberException | InvalidLottoTicketException e) {
            OutputView.errorMessage(e.getMessage());
            return getBonusNumber(winningLotto);
        }
    }

    private static LottoTicket getLottoTicket() {
        try {
            OutputView.inputWinningNumberInstruction();
            return new LottoTicket(InputView.getWinningNumbers());
        } catch (InvalidLottoNumberException | InvalidLottoTicketException e) {
            OutputView.errorMessage(e.getMessage());
            return getLottoTicket();
        }
    }

    private static LottoTickets getLottoTickets(Money money) {
        OutputView.ticketAmountInstruction(money);
        LottoTickets lottoTickets = LottoTickets.createLottoTickets(money);
        OutputView.lottoTicketList(lottoTickets);
        return lottoTickets;
    }

    private static Money getMoney() {
        try {
            OutputView.inputMoneyInstruction();
            return Money.create(InputView.getInput());
        } catch (InvalidMoneyException e) {
            OutputView.errorMessage(e.getMessage());
            return getMoney();
        }
    }
}
