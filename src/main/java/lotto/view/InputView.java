package lotto.view;

import lotto.Exception.DuplicationException;
import lotto.Exception.NotBuyLottoTicketException;
import lotto.Exception.NumberOutOfRangeException;
import lotto.domain.LottoBall;
import lotto.domain.PurchaseAmount;
import lotto.util.InputValidationUtil;
import lotto.util.WinningBallsUtils;

import java.util.List;
import java.util.Scanner;

public class InputView {
    static Scanner scanner = new Scanner(System.in);

    public static String inputPurchaseAmount() {
        try {
            return scanner.nextLine();
        } catch (NotBuyLottoTicketException e) {
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
        } catch (NumberOutOfRangeException | DuplicationException e) {
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
