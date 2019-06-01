package lotto;

import lotto.model.*;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class LottoLauncher {

    public static void main(String[] args) {
        //1
        Money money = generateMoney();
        //2
        ManualLottoCount manualLottoCount = generateManualLottoCount(money);
        //3
        Lottos manualLottos = generateManualLottos(manualLottoCount);
        //4
        Lottos automaticLottos = LottoMachine.generateLottos(money.getLottoCount() - manualLottos.size());

        OutputView.printLottos(manualLottos, automaticLottos);

        WinningLotto winningLotto = new WinningLotto(generateWinningLotto(), generateBonusNumber());

        Lottos totalLottos = manualLottos.append(automaticLottos);
        LottoResult lottoResult = new LottoResult(money, totalLottos.getPrizes(winningLotto));

        OutputView.printStatistics(lottoResult);
    }

    private static ManualLottoCount generateManualLottoCount(Money money) {
        try {
            int input = InputView.askManualLottoCount();
            return new ManualLottoCount(input, money);
        } catch (Exception e) {
            return generateManualLottoCount(money);
        }
    }

    private static List<Integer> generateWinningLotto() {
        try {
            return InputView.askWinningLottoNumbers();
        } catch (Exception e) {
            System.out.println("잘못된 당첨 번호입니다");
            return generateWinningLotto();
        }
    }

    private static int generateBonusNumber() {
        try {
            return InputView.askBonusNumber();
        } catch (Exception e) {
            System.out.println("잘못된 보너스 번호입니다");
            return generateBonusNumber();
        }
    }

    private static Money generateMoney() {
        try {
            String userInput = InputView.askMoney();
            return new Money(userInput);
        } catch (Exception e) {
            System.out.println("잘못된 구입 금액입니다");
            return generateMoney();
        }
    }

    private static Lottos generateManualLottos(ManualLottoCount manualLottoCount) {
        try {
            return LottoMachine.generateLottos(manualLottoCount);
        } catch (Exception e) {
            return generateManualLottos(manualLottoCount);
        }
    }

    public static List<Integer> getManualLottoNumbers() {
        return InputView.askManualLottoNumbers();
    }

}
