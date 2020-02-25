package lotto.controller;

import java.util.ArrayList;
import java.util.List;

import lotto.domain.LottoNumber;
import lotto.domain.LottoQuantity;
import lotto.domain.LottoResult;
import lotto.domain.LottoTicket;
import lotto.domain.LottoTickets;
import lotto.domain.Money;
import lotto.exceptions.InvalidLottoNumberException;
import lotto.exceptions.InvalidLottoQuantityException;
import lotto.exceptions.InvalidLottoTicketException;
import lotto.exceptions.InvalidMoneyException;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    public static void run() {
        Money money = getMoney();
        LottoQuantity quantity = getLottoQuantity(money);
        money.reduceAmountForAutoTicket(quantity);
        OutputView.inputManualNumberInstruction();
        LottoTickets tickets = getLottoTickets(money, getManualTickets(quantity));
        LottoTicket winningLotto = getWinnerTicket();
        LottoNumber bonus = getBonusNumber(winningLotto);
        LottoResult result = LottoResult.create(tickets, winningLotto, bonus);
        OutputView.prizeStatistics(result.getLottoResult());
        OutputView.profitRate(LottoResult.calculate(money, result.getLottoResult()));
    }

    private static LottoQuantity getLottoQuantity(Money money) {
        try {
            OutputView.manualLottoTicketQuantityInstruction();
            return LottoQuantity.create(InputView.getInput(), money);
        } catch (InvalidLottoQuantityException e) {
            OutputView.errorMessage(e.getMessage());
            return getLottoQuantity(money);
        }
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

    private static LottoTicket getWinnerTicket() {
        try {
            OutputView.inputWinningNumberInstruction();
            return new LottoTicket(InputView.getWinningNumbers());
        } catch (InvalidLottoNumberException | InvalidLottoTicketException e) {
            OutputView.errorMessage(e.getMessage());
            return getWinnerTicket();
        }
    }

    private static LottoTickets getLottoTickets(Money money, LottoTickets manualTickets) {
        OutputView.ticketAmountInstruction(money, manualTickets.getQuantity());
        LottoTickets tickets = LottoTickets.createLottoTickets(money, manualTickets);
        OutputView.lottoTicketList(tickets);
        return tickets;
    }

    private static LottoTickets getManualTickets(LottoQuantity lottoQuantity) {
        List<LottoTicket> manualTickets = new ArrayList<>();
        for (int i = 0, end = lottoQuantity.getQuantity(); i < end; i++) {
            manualTickets.add(getManualTicket());
        }
        return LottoTickets.createLottoTickets(manualTickets);
    }

    private static LottoTicket getManualTicket() {
        try {
            return new LottoTicket(InputView.getWinningNumbers());
        } catch (InvalidLottoNumberException | InvalidLottoTicketException e) {
            OutputView.errorMessage(e.getMessage());
            return getManualTicket();
        }
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
