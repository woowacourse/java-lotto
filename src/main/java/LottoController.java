import java.util.ArrayList;
import java.util.List;
import model.Lotto;
import model.UserLotto;
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
        UserLotto userLotto = new UserLotto(getPurchaseAmount());
        //3. 로또들 출력
        outputView.printPurchaseLottos(userLotto.getLottosDto());
        //4. 당첨 번호, 보너스 볼 입력
        //5. 당첨 통계, 수익률 출력
    }

    private String getPurchaseAmount() {
        while (true) {
            try {
                outputView.printPurchaseAmountGuide();
                return inputView.getPurchaseAmountInput();
            } catch (IllegalArgumentException ex) {
                outputView.printError(ex.getMessage());
            }
        }
    }
}
