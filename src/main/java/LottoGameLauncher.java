import domain.Lotto;
import domain.LottoFactory;
import domain.PurchaseAmount;
import view.InputView;
import view.OutputView;

import java.util.List;

public class LottoGameLauncher {
    public static void main(String[] args) {
        PurchaseAmount purchaseAmount = getPurchaseAmount();
        List<Lotto> autoIssuedLottos = LottoFactory.issueLottoWorthOf(purchaseAmount);
        OutputView.showIssuedLottos(autoIssuedLottos);
    }

    private static PurchaseAmount getPurchaseAmount() {
        try {
            int moneyFromPlayer = InputView.inputPurchaseAmount();
            return PurchaseAmount.of(moneyFromPlayer);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getPurchaseAmount();
        }
    }

    private static Lotto getWinningLotto() {
        try {
            List<Integer> inputNumbers = InputView.inputWinningNumbers();
            return LottoFactory.issueWinningLotto(inputNumbers);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getWinningLotto();
        }
    }
}
