package lotto.controller;

import java.util.List;

import lotto.domain.LottoResult;
import lotto.domain.LottoRule.LottoBalls;
import lotto.domain.LottoRule.LottoNumber;
import lotto.domain.LottoTicket;
import lotto.domain.LottoTickets;
import lotto.domain.Money;
import lotto.domain.WinningTicket;
import lotto.exceptions.InvalidLottoNumberException;
import lotto.exceptions.InvalidLottoTicketException;
import lotto.exceptions.InvalidManualQuantityException;
import lotto.exceptions.InvalidMoneyException;
import lotto.exceptions.InvalidWinningTicketException;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    public static void run() {
        Money money = getMoney();
        int manualQuantity = getManualQuantity(money);
        List<LottoTicket> manualTickets = getManualTickets(manualQuantity);
        LottoTickets lottoTickets = getLottoTickets(money, manualTickets);

        LottoTicket winningNumbers = getWinningNumbers();
        WinningTicket winningTicket = getWinningTicketWithBonusNumber(winningNumbers);
        LottoResult lottoResult = lottoTickets.match(winningTicket);
        OutputView.prizeStatistics(lottoResult);
        OutputView.profitRate(lottoResult.calculateRate(money));
    }

    private static List<LottoTicket> getManualTickets(int manualQuantity) {
        try {
            OutputView.inputManualNumbersInstruction(manualQuantity);
            List<String[]> manualNumbers = InputView.getManualNumbers(manualQuantity);
            return LottoTicket.createManualLottoTickets(manualNumbers);
        } catch (InvalidLottoTicketException | InvalidLottoNumberException e) {
            OutputView.errorMessage(e.getMessage());
            return getManualTickets(manualQuantity);
        }
    }

    private static WinningTicket getWinningTicketWithBonusNumber(LottoTicket winningLotto) {
        try {
            OutputView.inputBonusNumberInstruction();
            LottoNumber bonusNumber = LottoBalls.find(InputView.getInput());
            return new WinningTicket(winningLotto, bonusNumber);
        } catch (InvalidLottoNumberException | InvalidWinningTicketException e) {
            OutputView.errorMessage(e.getMessage());
            return getWinningTicketWithBonusNumber(winningLotto);
        }
    }

    private static LottoTicket getWinningNumbers() {
        try {
            OutputView.inputWinningNumberInstruction();
            return new LottoTicket(InputView.getWinningNumbers());
        } catch (InvalidLottoNumberException | InvalidLottoTicketException e) {
            OutputView.errorMessage(e.getMessage());
            return getWinningNumbers();
        }
    }

    private static LottoTickets getLottoTickets(Money money, List<LottoTicket> manualTickets) {
        OutputView.ticketAmountInstruction(money, manualTickets.size());
        LottoTickets lottoTickets = LottoTickets.createAutoAndAdd(money, manualTickets);
        OutputView.lottoTicketList(lottoTickets);
        return lottoTickets;
    }

    private static int getManualQuantity(Money money) {
        try {
            OutputView.changeInstruction(money);
            OutputView.inputManualQuantityInstruction();
            return money.getValidatedManualQuantity(InputView.getManualQuantity());
        } catch (InvalidManualQuantityException e) {
            OutputView.errorMessage(e.getMessage());
            return getManualQuantity(money);
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
