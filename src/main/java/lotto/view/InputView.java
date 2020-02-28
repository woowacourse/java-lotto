package lotto.view;

import lotto.Exception.*;
import lotto.domain.*;
import lotto.domain.LottoTicketNumber.ManualLottoTicketNumber;
import lotto.util.InputValidationUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class InputView {
    static Scanner scanner = new Scanner(System.in);

    private InputView() {
    }

    public static PurchaseAmount inputPurchaseAmount() {
        try {
            OutputView.printStartGuide();
            PurchaseAmount purchaseAmount = new PurchaseAmount(scanner.nextLine());
            OutputView.printChangeMoney(purchaseAmount.giveChangeMoney());
            return purchaseAmount;
        } catch (NotBuyLottoTicketException | NumberFormatException e) {
            OutputView.printErrorMessage(e.getMessage());
            return inputPurchaseAmount();
        }
    }

    public static ManualLottoTicketNumber inputManualTicketNumber(PurchaseAmount purchaseAmount) {
        try {
            OutputView.printManualTicketNumberGuide();
            int manualTicketNumber = InputValidationUtil.returnNumberWithNumberCheck(scanner.nextLine());
            InputValidationUtil.isPositiveNumber(manualTicketNumber);
            return new ManualLottoTicketNumber(manualTicketNumber, purchaseAmount.giveTotalLottoTicketNumber());
        } catch (NumberFormatException | NotPositiveNumberException | NumberOutOfRangeException e) {
            OutputView.printErrorMessage(e.getMessage());
            return inputManualTicketNumber(purchaseAmount);
        }
    }

    public static List<Set<LottoBall>> inputManualLottoTickets(ManualLottoTicketNumber manualLottoTicketNumber) {
        List<Set<LottoBall>> manualLottoBallsInputs = new ArrayList<>();
        OutputView.printManualLottoBallsGuide();
        for (int i = 0; i < manualLottoTicketNumber.getLottoTicketNumber(); i++) {
            manualLottoBallsInputs.add(InputView.InputWinningBallsAndManualLottoBalls());
        }
        return manualLottoBallsInputs;
    }

    private static Set<LottoBall> InputWinningBallsAndManualLottoBalls() {
        try {
            return LottoBalls.generateLottoBalls(scanner.nextLine());
        } catch (LottoTicketEmptyException | NumberOutOfRangeException | NumberFormatException | DuplicationException e) {
            OutputView.printErrorMessage(e.getMessage());
            return InputView.InputWinningBallsAndManualLottoBalls();
        }
    }

    private static LottoBall InputBonusBall() {
        try {
            OutputView.printAnswerBonusBall();
            String bonusBall = scanner.nextLine();
            return LottoBallFactory.findByLottoBall(InputValidationUtil.returnNumberWithNumberCheck(bonusBall));
        } catch (NumberFormatException e) {
            OutputView.printErrorMessage(e.getMessage());
            return InputBonusBall();
        }
    }

    public static WinningBalls generateWinningBalls() {
        try {
            OutputView.printAnswerWinningBalls();
            Set<LottoBall> winningBalls = InputWinningBallsAndManualLottoBalls();
            LottoBall bonusBall = InputBonusBall();
            return new WinningBalls(winningBalls, bonusBall);
        } catch (DuplicationException | NumberOutOfRangeException e) {
            OutputView.printErrorMessage(e.getMessage());
            return generateWinningBalls();
        }
    }
}