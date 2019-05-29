package lotto;

import lotto.domain.Lotto;
import lotto.domain.Lottos;
import lotto.domain.Money;
import lotto.domain.WinningLotto;
import lotto.view.InputConsole;
import lotto.view.OutputConsole;

public class Main {

    public static void main(String[] args) {
        Money money = createMoney();
        int numberOfLotto = money.getNumberOfLotto();
        Lottos lottos = new Lottos(numberOfLotto);
        OutputConsole.outputLotto(numberOfLotto, lottos);
        WinningLotto winningLotto = createWinningLotto();
        OutputConsole.outputResult(winningLotto, lottos);
    }

    private static Money createMoney() {
        try {
            return new Money(InputConsole.inputMoney());
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            return createMoney();
        }
    }

    private static WinningLotto createWinningLotto() {
        try {
            return new WinningLotto(new Lotto(InputConsole.inputWinningLotto()));
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            return createWinningLotto();
        }
    }
}
