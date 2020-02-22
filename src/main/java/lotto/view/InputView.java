package lotto.view;

import lotto.domain.LottoBall;
import lotto.domain.PurchaseAmount;
import lotto.domain.WinningTicketFactory;
import lotto.util.InputValidationUtil;

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

    public static List<LottoBall> InputWinningTicket() {
        try {
            OutputView.printAnswerWinningBalls();
            String winningTicket = scanner.nextLine();
            WinningTicketFactory winningTicketFactory = new WinningTicketFactory(winningTicket);
            return winningTicketFactory.getWinningTicket();
        } catch (RuntimeException e) {
            OutputView.printErrorMessage(e.getMessage());
            return InputWinningTicket();
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
