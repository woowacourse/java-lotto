package lotto.controller;

import lotto.domain.LottoResult;
import lotto.domain.LottoRule.LottoBalls;
import lotto.domain.LottoRule.LottoNumber;
import lotto.domain.LottoTicket;
import lotto.domain.LottoTickets;
import lotto.domain.Money;
import lotto.domain.WinningTicket;
import lotto.exceptions.InvalidLottoNumberException;
import lotto.exceptions.InvalidLottoTicketException;
import lotto.exceptions.InvalidMoneyException;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    public static void run() {
        Money money = getMoney();
        LottoTickets lottoTickets = getLottoTickets(money);
        LottoTicket winningLotto = getLottoTicket();
        LottoNumber bonusNumber = getBonusNumber(winningLotto);
        WinningTicket winningTicket = new WinningTicket(winningLotto, bonusNumber);
        LottoResult lottoResult = new LottoResult();
        OutputView.prizeStatistics(lottoResult.matchResult(winningTicket, lottoTickets));
        OutputView.profitRate(lottoResult.calculateRate(money));
    }

    private static LottoNumber getBonusNumber(LottoTicket winningLotto) {
        try {
            OutputView.inputBonusNumberInstruction();
            LottoNumber bonusNumber = LottoBalls.find(InputView.getInput());
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
