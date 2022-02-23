import controller.LottoController;
<<<<<<< HEAD
import domain.Result;
=======
>>>>>>> 6a49a84 (feat: 로또 구매 및 당첨번호 세팅 기능 구현)
import dto.LottosDto;
import view.InputView;
import view.OutputView;

import java.util.List;

public class Application {
    public static void main(String[] args) {
<<<<<<< HEAD
        InputView inputView = InputView.getInstance();
        OutputView outputView = OutputView.getInstance();
        LottoController lottoController = new LottoController();

        LottosDto lottosDto = lottoController.purchase(inputView.inputPurchaseAmount());
        outputView.printPurchasedLotto(lottosDto);

        lottoController.determineWinningNumber(inputView.inputWinningNumber(), inputView.inputBonusBall());

        outputView.printResult(lottoController.makeResult());
=======
        LottoController lottoController = new LottoController();

        int purchaseAmount = InputView.inputPurchaseAmount();
        LottosDto lottosDto = lottoController.purchase(purchaseAmount);
        OutputView.printPurchasedLotto(lottosDto);

        List<String> winningNumber = InputView.inputWinningNumber();
        int bonusBall = InputView.inputBonusBall();
        lottoController.determineWinningNumber(winningNumber,bonusBall);
>>>>>>> 6a49a84 (feat: 로또 구매 및 당첨번호 세팅 기능 구현)
    }
}
