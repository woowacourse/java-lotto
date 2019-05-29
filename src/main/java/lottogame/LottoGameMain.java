package lottogame;

import lottogame.domain.PurchaseLotto;
import lottogame.domain.WinningLotto;
import lottogame.utils.InvalidLottoNumberException;
import lottogame.utils.InvalidLottoPriceException;
import lottogame.utils.LottoNumbersParser;
import lottogame.view.InputView;
import lottogame.view.OutputView;

public class LottoGameMain {
    public static void main(String[] args) {
        PurchaseLotto purchaseLotto = createLottos();
        OutputView.printPurchaseResult(purchaseLotto);
        WinningLotto winningLotto = createWinningLotto();
    }

    private static PurchaseLotto createLottos() {
        try {
            return new PurchaseLotto(Integer.parseInt(InputView.getPrice()));
        } catch (NumberFormatException | InvalidLottoPriceException e) {
            System.out.println(e.getMessage());
            return createLottos();
        }
    }

    private static WinningLotto createWinningLotto() {
        try {
            return new WinningLotto(LottoNumbersParser.parse(InputView.getWinningLotto()));
        } catch (NumberFormatException | InvalidLottoNumberException e) {
            System.out.println(e.getMessage());
            return createWinningLotto();
        }
    }
}
