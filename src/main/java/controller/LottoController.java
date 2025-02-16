package controller;

import java.util.List;
import model.LottoResult;
import model.RandomNumberGenerator;
import model.UserLotto;
import model.WinningLotto;
import view.InputView;
import view.OutputView;

public class LottoController {

    private final InputView inputView;
    private final OutputView outputView;
    private final RandomNumberGenerator randomNumberGenerator;

    public LottoController(InputView inputView, OutputView outputView, RandomNumberGenerator randomNumberGenerator) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.randomNumberGenerator = randomNumberGenerator;
    }

    public void run() {
        UserLotto userLotto = getUserLotto();
        outputView.printPurchaseLottos(userLotto.toDto());

        WinningLotto winningLotto = getWinningLotto();

        LottoResult lottoResult = new LottoResult(userLotto, winningLotto);
        outputView.printResultRanks(lottoResult.toDto());
    }

    private UserLotto getUserLotto() {
        while (true) {
            try {
                outputView.printPurchaseAmountGuide();
                int purchaseAmountInput = inputView.getPurchaseAmountInput();
                return new UserLotto(randomNumberGenerator, purchaseAmountInput);
            } catch (IllegalArgumentException ex) {
                outputView.printError(ex.getMessage());
            }
        }
    }

    private WinningLotto getWinningLotto() {
        while (true) {
            try {
                outputView.printWinningNumberGuide();
                List<Integer> winningNumberInput = inputView.getWinningNumberInput();
                outputView.printBonusGuide();
                int bonusInput = inputView.getBonusInput();
                return new WinningLotto(winningNumberInput, bonusInput);
            } catch (IllegalArgumentException ex) {
                outputView.printError(ex.getMessage());
            }
        }
    }
}
