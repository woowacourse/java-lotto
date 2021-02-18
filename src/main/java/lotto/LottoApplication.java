package lotto;

import lotto.domain.Machine;
import lotto.domain.Result;
import lotto.utils.RandomLottoGenerator;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoApplication {
    public static void main(String[] args) {
        final String moneyValue = InputView.getMoneyInput();

        final Machine machine = new Machine(moneyValue, new RandomLottoGenerator());
        OutputView.printTickets(machine.getTickets());
        Result result = machine
            .getResult(InputView.getWinningNumbersInput(), InputView.getBonusBallInput());

        OutputView.printResult(result);
        InputView.closeScanner();
    }

}
