package lotto;

import java.util.List;
import lotto.domain.lotto.LottoMachine;
import lotto.domain.lotto.LottoTicket;
import lotto.domain.lotto.Money;
import lotto.domain.result.LottoMatcher;
import lotto.domain.result.Result;
import lotto.utils.RandomLottoGenerator;
import lotto.view.InputView;
import lotto.view.ResultView;
import lotto.view.TicketsView;

public class LottoApplication {

    public static void main(String[] args) {
        Money money = getMoneyByInput();

        List<LottoTicket> lottoTickets = buyLottoTickets(money);
        TicketsView.printTickets(lottoTickets);

        Result result = getMatchedLottoResult(money, lottoTickets);
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

    private static List<LottoTicket> buyLottoTickets(Money money) {
        LottoMachine lottoMachine = new LottoMachine(money);
        return lottoMachine.buyTickets(new RandomLottoGenerator());
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
