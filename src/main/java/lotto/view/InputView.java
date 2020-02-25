package lotto.view;

import lotto.domain.LottoBall;
import lotto.domain.LottoTicket;
import lotto.domain.PurchaseAmount;
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
            LottoTicket winningTicketFactory = generateLottoTicket();
            return winningTicketFactory.getLottoTicket();
        } catch (RuntimeException e) {
            OutputView.printErrorMessage(e.getMessage());
            return InputWinningTicket();
        }
    }

    private static LottoTicket generateLottoTicket() {
        String winningTicket = scanner.nextLine();
        return new LottoTicket(winningTicket);
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
