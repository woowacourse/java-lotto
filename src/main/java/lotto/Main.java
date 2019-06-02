package lotto;

import lotto.domain.*;
import lotto.view.InputConsole;
import lotto.view.OutputConsole;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Money money = createMoney();
        LottoCount lottoCount = createLottoCount(money);
        List<Lotto> inputLottos = createLottos(lottoCount.getManualCount(), lottoCount.getAutoCount());
        Lottos lottos = new Lottos(inputLottos);
        OutputConsole.outputLotto(lottos, lottoCount.getManualCount(), lottoCount.getAutoCount());
        WinningLotto winningLotto = createWinningLotto(createLastWinningLotto());
        OutputConsole.outputResult(new LottoResult(winningLotto, lottos));
    }

    private static Money createMoney() {
        try {
            return new Money(InputConsole.inputMoney());
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            return createMoney();
        }
    }

    private static LottoCount createLottoCount(Money money) {
        try {
            return new LottoCount(money, InputConsole.inputNumberOfManualLotto());
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            return createLottoCount(money);
        }
    }

    private static List<Lotto> createLottos(int numberOfManualLotto, int numberOfAutoLotto) {
        System.out.println("\n수동으로 구매할 번호를 입력해 주세요.");
        List<Lotto> lottos = new ArrayList<>();
        try {
            lottos = new ArrayList<>(createManualLotto(numberOfManualLotto));
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            return createLottos(numberOfManualLotto, numberOfAutoLotto);
        }
        createAutoLotto(numberOfAutoLotto, lottos);
        return lottos;
    }

    private static List<Lotto> createManualLotto(int numberOfManualLotto) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < numberOfManualLotto; i++) {
            LottoMachine lottoMachine = new ManualLottoMachine(InputConsole.inputLotto());
            lottos.add(lottoMachine.generateLotto());
        }
        return lottos;
    }

    private static void createAutoLotto(int numberOfAutoLotto, List<Lotto> lottos) {
        LottoMachine lottoMachine = new AutoLottoMachine();
        for (int i = 0; i < numberOfAutoLotto; i++) {
            lottos.add(lottoMachine.generateLotto());
        }
    }

    private static WinningLotto createWinningLotto(Lotto lastWinningLotto) {
        try {
            return new WinningLotto(lastWinningLotto, LottoNumber.getInstance(InputConsole.inputBonusNumber()));
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            return createWinningLotto(lastWinningLotto);
        }
    }

    private static Lotto createLastWinningLotto() {
        System.out.println("\n지난 주 당첨 번호를 입력해주세요.");
        try {
            LottoMachine lottoMachine = new ManualLottoMachine(InputConsole.inputLotto());
            return lottoMachine.generateLotto();
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            return createLastWinningLotto();
        }
    }
}
