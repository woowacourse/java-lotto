package lotto.contoller;

import lotto.domain.LottoResult;
import lotto.domain.Lottos;
import lotto.domain.PurchaseAmount;
import lotto.domain.Winning;
import lotto.view.input.InputView;
import lotto.view.output.OutputView;

public class Main {
    public static void main(String[] args) {
        PurchaseAmount purchaseAmount = createMoney();
        Lottos lottos = Lottos.generate(purchaseAmount);
        OutputView.outputLottoCount(purchaseAmount);
        OutputView.outputLottos(lottos);
        Winning winning = createWinningLotto();
        LottoResult lottoResult = LottoResult.of(winning, lottos);
        OutputView.outputResult(lottoResult);

    }

    private static Winning createWinningLotto() {
        try {
            return Winning.of(InputView.inputWinningLotto());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return createWinningLotto();
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
