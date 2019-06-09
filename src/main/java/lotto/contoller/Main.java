package lotto.contoller;

import lotto.domain.lotto.InvalidLottoException;
import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.Lottos;
import lotto.domain.lotto.LottosGenerator;
import lotto.domain.purchase.InvalidPurchaseAmountException;
import lotto.domain.purchase.InvalidPurchaseCountException;
import lotto.domain.purchase.PurchaseAmount;
import lotto.domain.purchase.PurchaseCount;
import lotto.domain.result.InvalidWinningException;
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

    private static PurchaseAmount createPurchaseAmount() {
        try {
            return PurchaseAmount.of(InputView.inputMoney());
        } catch (InvalidPurchaseAmountException e) {
            System.out.println(e.getMessage());
            return createPurchaseAmount();
        }
    }

    private static PurchaseCount createManualCount(PurchaseAmount purchaseAmount) {
        try {
            return PurchaseCount.of(purchaseAmount, InputView.inputManualCount());
        } catch (InvalidPurchaseCountException e) {
            System.out.println(e.getMessage());
            return createManualCount(purchaseAmount);
        }
    }

    private static Lottos createLottos(PurchaseCount purchaseCount) {
        try {
            return Lottos.of(purchaseCount, InputView.inputLottos(purchaseCount));
        } catch (InvalidLottoException e) {
            System.out.println(e.getMessage());
            return createLottos(purchaseCount);
        }
    }

    private static Lotto createLotto() {
        try {
            return Lotto.of(InputView.inputWinningLotto());
        } catch (InvalidLottoException e) {
            System.out.println(e.getMessage());
            return createLotto();
        }
    }

    private static Winning createWinningLotto(Lotto lotto) {
        try {
            return Winning.of(lotto, InputView.inputBonusNum());
        } catch (InvalidWinningException e) {
            System.out.println(e.getMessage());
            return createWinningLotto(lotto);
        }
    }
}
