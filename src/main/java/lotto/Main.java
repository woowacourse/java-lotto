package lotto;

import lotto.domain.*;
import lotto.view.InputConsole;
import lotto.view.OutputConsole;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Money money = createMoney();
        int numberOfManualLotto = createNumberOfManualLotto();
        int numberOfAutoLotto = money.getNumberOfLotto() - numberOfManualLotto;
        System.out.println("\n수동으로 구매할 번호를 입력해 주세요.");
        Lottos lottos = new Lottos(createManualLotto(numberOfManualLotto), numberOfAutoLotto);
        OutputConsole.outputLotto(lottos, numberOfManualLotto, numberOfAutoLotto);
        System.out.println("\n지난 주 당첨 번호를 입력해주세요.");
        WinningLotto winningLotto = createWinningLotto(createLotto());
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

    private static int createNumberOfManualLotto() {
        try {
            return InputConsole.inputNumberOfManualLotto();
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            return createNumberOfManualLotto();
        }
    }

    private static List<Lotto> createManualLotto(int numberOfManualLotto) {
        List<Lotto> manualLottos = new ArrayList<>();
        for (int i = 0; i < numberOfManualLotto; i++) {
            manualLottos.add(createLotto());
        }
        return manualLottos;
    }

    private static WinningLotto createWinningLotto(Lotto lastWinningLotto) {
        try {
            return new WinningLotto(lastWinningLotto, LottoNumber.getInstance(InputConsole.inputBonusNumber()));
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            return createWinningLotto(lastWinningLotto);
        }
    }

    private static Lotto createLotto() {
        try {
            return new Lotto(InputConsole.inputLotto());
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            return createLotto();
        }
    }
}
