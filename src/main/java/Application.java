import controller.LottoController;
import domain.Result;
import dto.LottosDto;
import view.InputView;
import view.OutputView;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        LottoController lottoController = new LottoController();

        LottosDto lottosDto = lottoController.purchase(InputView.inputPurchaseAmount());
        OutputView.printPurchasedLotto(lottosDto);

        lottoController.determineWinningNumber(InputView.inputWinningNumber(), InputView.inputBonusBall());

        List<Result> results = lottoController.judgeLottos();
        OutputView.printResult(lottoController.makeResult(results));
    }
}
