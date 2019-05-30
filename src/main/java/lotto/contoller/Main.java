package lotto.contoller;

import lotto.domain.*;
import lotto.view.input.InputView;
import lotto.view.output.OutputView;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        PurchaseCount purchaseCount = createManualCount(createPurchaseAmount());
        Lottos lottos = createLottos(purchaseCount);

        OutputView.outputLottoCount(purchaseCount);
        OutputView.outputLottos(lottos);

        Winning winning = createWinningLotto(createLotto());

        OutputView.outputResult(LottoResult.of(winning, lottos));
    }

    private static Lottos createLottos(PurchaseCount purchaseCount) {
        try {
            return LottosGenerator.generate(purchaseCount, createManualLottos(purchaseCount));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return createLottos(purchaseCount);
        }
    }

    private static List<List<Integer>> createManualLottos(PurchaseCount purchaseCount) {
        return InputView.inputLottos(purchaseCount);
    }

    private static PurchaseCount createManualCount(PurchaseAmount purchaseAmount) {
        try {
            return PurchaseCount.of(purchaseAmount, InputView.inputManualCount());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return createManualCount(purchaseAmount);
        }
    }

    private static PurchaseAmount createPurchaseAmount() {
        try {
            return PurchaseAmount.of(InputView.inputMoney());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return createPurchaseAmount();
        }
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
}
