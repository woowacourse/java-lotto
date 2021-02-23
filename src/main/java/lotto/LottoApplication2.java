package lotto;

import java.util.Collections;
import java.util.List;
import lotto.domain.lotto.LottoMachine2;
import lotto.domain.lotto.LottoTicket;
import lotto.domain.lotto.Money;
import lotto.domain.result.LottoMatcher;
import lotto.domain.result.Result;
import lotto.domain.result.UsersLottoTickets;
import lotto.utils.NumericStringValidator;
import lotto.view.InputView;
import lotto.view.ResultView;
import lotto.view.TicketsView;

public class LottoApplication2 {

    public static void main(String[] args) {
        Money money = getMoneyByInput();

        UsersLottoTickets usersLottoTickets = getLottoGroup(money);

        TicketsView.printTickets(usersLottoTickets);

        Result result = getMatchedLottoResult(money, usersLottoTickets.getTotalTickets());
        ResultView.printResult(result);
    }

    private static Money getMoneyByInput() {
        try {
            return Money.valueOf(InputView.getMoneyInput());
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return getMoneyByInput();
        }
    }

    private static UsersLottoTickets getLottoGroup(Money money) {
        try {
            return buyLottoTickets(money, getManualTicketsValue());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getLottoGroup(money);
        }
    }

    private static List<String> getManualTicketsValue() {
        int amount = getManuallyBuyAmountByInput();
        if (amount < 1) {
            return Collections.emptyList();
        }
        return InputView.getManualLottoTicketsInput(amount);
    }

    private static int getManuallyBuyAmountByInput() {
        String input = InputView.getManuallyBuyAmountInput();
        if (!NumericStringValidator.isValid(input)) {
            System.out.println("0 이상의 정수만 가능합니다.");
            return getManuallyBuyAmountByInput();
        }
        return Integer.parseInt(input);
    }

    private static UsersLottoTickets buyLottoTickets(Money money, List<String> manualTicketsValue) {
        LottoMachine2 lottoMachine = new LottoMachine2(money);
        return lottoMachine.buyTickets(manualTicketsValue);
    }

    private static Result getMatchedLottoResult(Money money, List<LottoTicket> lottoTickets) {
        try {
            LottoMatcher lottoMatcher = new LottoMatcher(
                    InputView.getWinningNumbersInput(),
                    InputView.getBonusBallInput(), lottoTickets, money);
            return lottoMatcher.getResult();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return getMatchedLottoResult(money, lottoTickets);
        }
    }
}
