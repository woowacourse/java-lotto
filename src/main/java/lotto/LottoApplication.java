package lotto;

import java.util.List;
import lotto.domain.Machine;
import lotto.domain.lotto.LottoTicket;
import lotto.domain.result.Result;
import lotto.utils.RandomLottoGenerator;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoApplication {

    public static void main(String[] args) {
        String moneyValue = InputView.getMoneyInput();
        Machine machine = new Machine(moneyValue);

        List<LottoTicket> lottoTickets = machine.buyTickets(new RandomLottoGenerator());

        OutputView.printTickets(lottoTickets);

        String winnerNumbersValue = InputView.getWinningNumbersInput();
        String bonusBallValue = InputView.getBonusBallInput();
        Result result = machine.getResult(winnerNumbersValue, bonusBallValue, lottoTickets);

        OutputView.printResult(result);
    }
}
