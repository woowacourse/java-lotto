package lotto.view;

import lotto.domain.LottoBall;
import lotto.domain.PurchaseAmount;
import lotto.util.InputValidationUtil;
import lotto.util.WinningBallsUtils;

import java.util.List;
import java.util.Scanner;

public class InputView {
    static Scanner scanner = new Scanner(System.in);

    public static PurchaseAmount inputPurchaseAmount() {
        try {
            String purchaseAmountInput = scanner.nextLine();
            return new PurchaseAmount(purchaseAmountInput);
        } catch (RuntimeException e) {
            OutputView.printErrorMessage(e.getMessage());
            return inputPurchaseAmount();
        }
    }

    public static List<LottoBall> InputWinningBalls() {
        try {
            OutputView.printAnswerWinningBalls();
            String winningBalls = scanner.nextLine();
            WinningBallsUtils winningBallsUtils = new WinningBallsUtils(winningBalls);
            return winningBallsUtils.getWinningBalls();
        } catch (RuntimeException e) {
            OutputView.printErrorMessage(e.getMessage());
            return InputWinningBalls();
        }
    }

    public static int InputBonusBall() {
        try {
            OutputView.printAnswerBonusBall();
            String bonusBall = scanner.nextLine();
            return InputValidationUtil.returnNumberWithNumberCheck(bonusBall);
        } catch (RuntimeException e) {
            OutputView.printErrorMessage(e.getMessage());
            return InputBonusBall();
        }
    }
}
