package lotto.contoller;

import lotto.domain.lotto.InvalidLotto;
import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.Lottos;
import lotto.domain.lotto.LottosGenerator;
import lotto.domain.purchase.InvalidPurchaseAmount;
import lotto.domain.purchase.InvalidPurchaseCount;
import lotto.domain.purchase.PurchaseAmount;
import lotto.domain.purchase.PurchaseCount;
import lotto.domain.result.InvalidWinning;
import lotto.domain.result.LottoResult;
import lotto.domain.result.Winning;
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
        } catch (InvalidLotto e) {
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
        } catch (InvalidPurchaseCount e) {
            System.out.println(e.getMessage());
            return createManualCount(purchaseAmount);
        }
    }

    private static PurchaseAmount createPurchaseAmount() {
        try {
            return PurchaseAmount.of(InputView.inputMoney());
        } catch (InvalidPurchaseAmount e) {
            System.out.println(e.getMessage());
            return createPurchaseAmount();
        }
    }

    private static Lotto createLotto() {
        try {
            return Lotto.of(InputView.inputWinningLotto());
        } catch (InvalidLotto e) {
            System.out.println(e.getMessage());
            return createLotto();
        }
    }

    private static Winning createWinningLotto(Lotto lotto) {
        try {
            return Winning.of(lotto, InputView.inputBonusNum());
        } catch (InvalidWinning e) {
            System.out.println(e.getMessage());
            return createWinningLotto(lotto);
        }
    }
}
