package lotto.contoller;

import lotto.domain.*;
import lotto.view.input.InputView;
import lotto.view.output.OutputView;

public class Main {
    public static void main(String[] args) {
        PurchaseAmount purchaseAmount = createMoney();
        Lottos lottos = Lottos.generate(purchaseAmount);

        OutputView.outputLottoCount(purchaseAmount);
        OutputView.outputLottos(lottos);

        Winning winning = createWinningLotto(createLotto());

        OutputView.outputResult(LottoResult.of(winning, lottos));
    }

    private static Lotto createLotto() {
        try {
            return Lotto.of(InputView.inputWinningLotto());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return createLotto();
        }
    }

    private static Winning createWinningLotto(Lotto lotto) {
        try {
            return Winning.of(lotto, InputView.inputBonusNum());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return createWinningLotto(lotto);
        }
    }

    private static PurchaseAmount createMoney() {
        try {
            return PurchaseAmount.of(InputView.inputMoney(), 0);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return createMoney();
        }
    }
}
