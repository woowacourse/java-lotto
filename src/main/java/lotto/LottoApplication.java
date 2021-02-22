package lotto;

import lotto.domain.Machine;
import lotto.domain.Result;
import lotto.utils.CustomException;
import lotto.utils.RandomLottoGenerator;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoApplication {
    public static void main(String[] args) {
        runApplicationUntilValid();
        InputView.closeScanner();
    }

    private static void runApplicationUntilValid() {
        try {
            runApplication();
        } catch (CustomException c) {
            System.out.println(c.getMessage());
            OutputView.printRestart();
            runApplicationUntilValid();
        }
    }

    private static void runApplication() {
        final String moneyValue = InputView.getMoneyInput();

        final Machine machine = new Machine(moneyValue, new RandomLottoGenerator());
        OutputView.printTickets(machine.getTickets(), machine.getChange());
        Result result = machine
            .getResult(InputView.getWinningNumbersInput(), InputView.getBonusBallInput());

        OutputView.printResult(result);
    }

}
