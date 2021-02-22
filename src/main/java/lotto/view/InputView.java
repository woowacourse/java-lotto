package lotto.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import lotto.utils.InputValidationUtils;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {}

    public static int inputPurchaseAmount() {
        try {
            OutputView.printPurchaseAmountGuideMessage();
            int purchaseAmountValue = Integer.parseInt(scanner.nextLine());
            InputValidationUtils.validatePurchaseAmount(purchaseAmountValue);
            return purchaseAmountValue;
        } catch (Exception e) {
            OutputView.printExceptionMessage(e);
            return inputPurchaseAmount();
        }
    }

    public static List<Integer> inputWinningLottoNumbers() {
        try {
            OutputView.printWinningLottoGuideMessage();
            String[] input = scanner.nextLine().split(",");
            return Arrays.stream(input)
                .map(number -> number.trim())
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        } catch (Exception e) {
            OutputView.printExceptionMessage(e);
            return inputWinningLottoNumbers();
        }

    }

    public static int inputWinningBonus() {
        try {
            OutputView.printWinningLottoBonusGuideMessage();
            int bonusValue = Integer.parseInt(scanner.nextLine());
            InputValidationUtils.validateWinningBonus(bonusValue);
            return bonusValue;
        } catch (Exception e) {
            OutputView.printExceptionMessage(e);
            return inputWinningBonus();
        }
    }
}
