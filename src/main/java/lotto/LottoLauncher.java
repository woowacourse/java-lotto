package lotto;

import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LottoLauncher {

    public static void main(String[] args) {
        Money money = generateMoney();
        Lottos manualLottos = generateManualLottos(money);
        Lottos automaticLottos = LottoMachine.generateLottos(money.getLottoCount() - manualLottos.size());

        OutputView.printLottos(manualLottos, automaticLottos);

        WinningLotto winningLotto = new WinningLotto(generateWinningLotto(), InputView.askBonusNumber());

        Lottos totalLottos = manualLottos.append(automaticLottos);
        LottoResult lottoResult = new LottoResult(money, totalLottos.getPrizes(winningLotto));

        OutputView.printStatistics(lottoResult);
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

    private static Lottos generateManualLottos(Money money) {
        try {
            List<Lotto> manualLottos = new ArrayList<>();
            int count = InputView.askManualLottoCount();
            if (count < 0 || count > money.getLottoCount()) {
                throw new IllegalCountException();
            }
            for (int i = 0; i < count; i++) {
                manualLottos.add(new Lotto(InputView.askManualLottoNumbers()));
            }
            return LottoMachine.generateLottos(manualLottos);
        } catch (Exception e) {
            return generateManualLottos(money);
        }

    }

}
