package lotto.view;

import lotto.Exception.DuplicationException;
import lotto.Exception.NotBuyLottoTicketException;
import lotto.Exception.NotPositiveNumberException;
import lotto.Exception.NumberOutOfRangeException;
import lotto.domain.LottoBall;
import lotto.domain.LottoBallFactory;
import lotto.domain.LottoBalls;
import lotto.domain.PurchaseAmount;
import lotto.util.InputValidationUtil;

import java.util.Scanner;
import java.util.Set;

public class InputView {
    static Scanner scanner = new Scanner(System.in);

    private InputView() { }

    public static PurchaseAmount inputPurchaseAmount() {
        try {
            OutputView.printStartGuide();
            String purchaseAmount = scanner.nextLine();
            return new PurchaseAmount(purchaseAmount);
        } catch (NotBuyLottoTicketException e) {
            OutputView.printErrorMessage(e.getMessage());
            return inputPurchaseAmount();
        }
    }

    public static int inputManualTicketNumber() {
        try {
            OutputView.printManualTicketNumberGuide();
            int manualTicketNumber = InputValidationUtil.returnNumberWithNumberCheck(scanner.nextLine());
            InputValidationUtil.isPositiveNumber(manualTicketNumber);
            return manualTicketNumber;
        }catch (NumberFormatException | NotPositiveNumberException e){
            OutputView.printErrorMessage(e.getMessage());
            return inputManualTicketNumber();
        }
    }

    public static Set<LottoBall> InputWinningBalls() {
        try {
            OutputView.printAnswerWinningBalls();
            String winningBalls = scanner.nextLine();
            return LottoBalls.generateLottoBalls(winningBalls);
        } catch (NumberOutOfRangeException | DuplicationException e) {
            OutputView.printErrorMessage(e.getMessage());
            return InputWinningBalls();
        }
    }

    public static LottoBall InputBonusBall() {
        try {
            OutputView.printAnswerBonusBall();
            String bonusBall = scanner.nextLine();
            return LottoBallFactory.findByLottoBall(InputValidationUtil.returnNumberWithNumberCheck(bonusBall));
        } catch (NumberFormatException e) {
            OutputView.printErrorMessage(e.getMessage());
            return InputBonusBall();
        }
    }
}
