package lotto;

import java.math.BigInteger;
import java.util.Collections;
import java.util.List;
import lotto.domain.lotto.LottoMachine;
import lotto.domain.lotto.LottoTicket;
import lotto.domain.lotto.ManualBuyAmount;
import lotto.domain.lotto.Money;
import lotto.domain.result.LottoMatcher;
import lotto.domain.result.Result;
import lotto.domain.result.UsersLottoTickets;
import lotto.view.InputView;
import lotto.view.ResultView;
import lotto.view.TicketsView;

public class LottoApplication {

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
            return buyLottoTickets(money);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getLottoGroup(money);
        }
    }

    private static UsersLottoTickets buyLottoTickets(Money money) {
        ManualBuyAmount manualAmount = ManualBuyAmount
                .getInstance(InputView.getManualBuyAmountInput(), money);

        LottoMachine lottoMachine = LottoMachine.getInstance(money, manualAmount);

        List<String> ticketsValue = getManualTicketsValue(lottoMachine.getManualBuyAmount());

        return lottoMachine.buyTickets(ticketsValue);
    }

    private static List<String> getManualTicketsValue(BigInteger amount) {
        if (amount.equals(BigInteger.ZERO)) {
            return Collections.emptyList();
        }
        return InputView.getManualLottoTicketsInput(amount);
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
