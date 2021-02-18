package lotto;

import lotto.domain.Machine;
import lotto.domain.Result;
import lotto.utils.RandomLottoGenerator;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoApplication {
    public static void main(String[] args) {
        String moneyValue = InputView.getMoneyInput();
        Machine machine = new Machine(moneyValue, new RandomLottoGenerator());

        OutputView.printTickets(machine.getTickets());

        String winnerNumbersValue = InputView.getWinningNumbersInput();
        String bonusBallValue = InputView.getBonusBallInput();
        Result result = machine.getResult(winnerNumbersValue, bonusBallValue);

        OutputView.printResult(result);
    }

}
