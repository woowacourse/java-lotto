import controller.LottoGame;
import domain.*;
import view.InputView;
import view.OutputView;

public class LottoGameApplication {

    public static void main(String[] args) {
        Money money = inputPurchaseAmountWithValidation();
        ManualCount manualCount = inputManualCountWithValidation(money);
        LottoCount lottoCount = new LottoCount(money.getLottoCount(), manualCount.getManualCount());

        LottoGame lottoGame = new LottoGame(lottoCount);
        lottoGame.calculateResults();
        lottoGame.showResult(money);
    }

    private static Money inputPurchaseAmountWithValidation() {
        try {
            return new Money(InputView.inputPurchaseAmount());
        } catch (IllegalArgumentException | NullPointerException e) {
            OutputView.printExceptionMessage(e);
            return inputPurchaseAmountWithValidation();
        }
    }

    private static ManualCount inputManualCountWithValidation(final Money money) {
        try {
            return new ManualCount(InputView.inputManualCount(), money.getLottoCount());
        } catch (IllegalArgumentException e) {
            OutputView.printExceptionMessage(e);
            return inputManualCountWithValidation(money);
        }
    }

}
