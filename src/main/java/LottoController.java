import java.util.ArrayList;
import java.util.List;
import model.Lotto;
import model.UserLotto;
import model.WinningLotto;
import view.InputValidator;
import view.InputView;
import view.OutputView;

public class LottoController {

    private final InputView inputView;
    private final OutputView outputView;

    public LottoController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        //1. 구입 금액 입력 + 로또 생성
        UserLotto userLotto = getUserLotto();
        //3. 로또들 출력
        outputView.printPurchaseLottos(userLotto.getLottosDto());
        //4. 당첨 번호, 보너스 볼 입력
        WinningLotto winningLotto = getWinningLotto();
        //5. 당첨 통계, 수익률 출력
    }

    private UserLotto getUserLotto() {
        while (true) {
            try {
                outputView.printPurchaseAmountGuide();
                String purchaseAmountInput = inputView.getPurchaseAmountInput();
                return new UserLotto(purchaseAmountInput);
            } catch (IllegalArgumentException ex) {
                outputView.printError(ex.getMessage());
            }
        }
    }

    private WinningLotto getWinningLotto() {
        while (true) {
            try {
                outputView.printWinningNumberGuide();
                String winningNumberInput = inputView.getWinningNumberInput();
                return new WinningLotto(winningNumberInput);
            } catch (IllegalArgumentException ex) {
                outputView.printError(ex.getMessage());
            }
        }
    }
}
