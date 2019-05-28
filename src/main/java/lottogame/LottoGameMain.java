package lottogame;

import lottogame.domain.PurchaseLotto;
import lottogame.utils.InvalidLottoPriceException;
import lottogame.view.InputView;
import lottogame.view.OutputView;

public class LottoGameMain {
    public static void main(String[] args) {
        PurchaseLotto purchaseLotto = createLottos();
        OutputView.printPurchaseResult(purchaseLotto);
    }

    private static PurchaseLotto createLottos() {
        try {
            return new PurchaseLotto(Integer.parseInt(InputView.getPrice()));
        } catch (NumberFormatException | InvalidLottoPriceException e) {
            System.out.println(e.getMessage());
            return createLottos();
        }
    }
}
