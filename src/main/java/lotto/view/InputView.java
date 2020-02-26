package lotto.view;

import lotto.Exception.NotBuyLottoTicketException;
import lotto.Exception.NotPositiveNumberException;
import lotto.Exception.NumberOutOfRangeException;
import lotto.domain.LottoBall;
import lotto.domain.LottoBallFactory;
import lotto.domain.LottoTicketNumber;
import lotto.domain.PurchaseAmount;
import lotto.util.InputValidationUtil;

import java.util.Scanner;

public class InputView {
    static Scanner scanner = new Scanner(System.in);

    private InputView() {
    }

    public static PurchaseAmount inputPurchaseAmount() {
        try {
            OutputView.printStartGuide();
            String purchaseAmount = scanner.nextLine();
            return new PurchaseAmount(purchaseAmount);
        } catch (NotBuyLottoTicketException | NumberFormatException e) {
            OutputView.printErrorMessage(e.getMessage());
            return inputPurchaseAmount();
        }
    }

    public static LottoTicketNumber inputManualTicketNumber(PurchaseAmount purchaseAmount) {
        try {
            OutputView.printManualTicketNumberGuide();
            int manualTicketNumber = InputValidationUtil.returnNumberWithNumberCheck(scanner.nextLine());
            InputValidationUtil.isPositiveNumber(manualTicketNumber);
            return new LottoTicketNumber(purchaseAmount.giveLottoTicketNumber(), manualTicketNumber);
        } catch (NumberFormatException | NotPositiveNumberException | NumberOutOfRangeException e) {
            OutputView.printErrorMessage(e.getMessage());
            return inputManualTicketNumber(purchaseAmount);
        }
    }

    public static String InputWinningBallsAndManualLottoBalls() {
        return scanner.nextLine();
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