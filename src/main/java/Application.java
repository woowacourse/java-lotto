import controller.LottoController;
import dto.LottosDto;
import view.InputView;
import view.OutputView;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        LottoController lottoController = new LottoController();

        int purchaseAmount = InputView.inputPurchaseAmount();
        LottosDto lottosDto = lottoController.purchase(purchaseAmount);
        OutputView.printPurchasedLotto(lottosDto);

        List<String> winningNumber = InputView.inputWinningNumber();
        int bonusBall = InputView.inputBonusBall();
        lottoController.determineWinningNumber(winningNumber,bonusBall);
    }
}
