package controller;

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
        outputView.printPurchaseLottos(userLotto.getSortedLottosDto());

        WinningLotto winningLotto = getWinningLotto();
        setBonus(winningLotto);

        LottoResult lottoResult = new LottoResult(userLotto, winningLotto);
        outputView.printResultRanks(lottoResult.getRanks());
        outputView.printProfitRate(lottoResult.getProfitRate());
    }

    private void setBonus(WinningLotto winningLotto) {
        while (true) {
            try {
                outputView.printBonusGuide();
                String bonusInput = inputView.getBonusInput();
                winningLotto.setBonus(bonusInput);
                return;
            } catch (IllegalArgumentException ex) {
                outputView.printError(ex.getMessage());
            }
        }
    }

    private UserLotto getUserLotto() {
        while (true) {
            try {
                outputView.printPurchaseAmountGuide();
                String purchaseAmountInput = inputView.getPurchaseAmountInput();
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
                String winningNumberInput = inputView.getWinningNumberInput();
                return new WinningLotto(winningNumberInput);
            } catch (IllegalArgumentException ex) {
                outputView.printError(ex.getMessage());
            }
        }
    }
}
