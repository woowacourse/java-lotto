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
        Lottos lottos = createLottos(lottoCount);

        OutputConsole.outputLotto(lottos, lottoCount);
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

    private static Lottos createLottos(LottoCount lottoCount) {
        try {
            LottosFactory lottosFactory = new LottosFactory(
                    createManualLottoNumbers(lottoCount.getManualCount()),
                    lottoCount);
            return lottosFactory.getLottos();
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            return createLottos(lottoCount);
        }
    }

    private static List<List<Integer>> createManualLottoNumbers(int manualCount)
            throws IllegalArgumentException {
        System.out.println("\n수동으로 구매할 번호를 입력해 주세요.");
        List<List<Integer>> numbersList = new ArrayList<>();
        for (int i = 0; i < manualCount; i++) {
            numbersList.add(InputConsole.inputLotto());
        }
        return numbersList;
    }

    private static WinningLotto createWinningLotto(Lotto lastWinningLotto) {
        try {
            return new WinningLotto(
                    lastWinningLotto,
                    LottoNumber.getInstance(InputConsole.inputBonusNumber()));
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            return createWinningLotto(lastWinningLotto);
        }
    }

    private static Lotto createLastWinningLotto() {
        System.out.println("지난 주 당첨 번호를 입력해주세요.");
        try {
            LottoMachine lottoMachine = new ManualLottoMachine(InputConsole.inputLotto());
            return lottoMachine.generateLotto();
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            return createLastWinningLotto();
        }
    }
}
