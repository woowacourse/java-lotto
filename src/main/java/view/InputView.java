package view;

import java.util.Arrays;
import java.util.Scanner;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String COMMA = ",";

    public static int inputPurchaseAmount() {
        OutputView.printInputPurchaseAmountMessage();
        try {
            return inputIntegerValueWithValidation();
        } catch (NumberFormatException e) {
            OutputView.printExceptionMessage(e.getMessage());
            return inputPurchaseAmount();
        }
    }

    public static int inputManualCount() {
        OutputView.printInputManualCountMessage();
        try {
            return inputIntegerValueWithValidation();
        } catch (NumberFormatException e) {
            OutputView.printExceptionMessage(e.getMessage());
            return inputManualCount();
        }
    }

    public static int[] inputManualLottoNumbers() {
        OutputView.printInputManualLottoNumbersMessage();
        try {
            return inputIntegerArrayWithValidation();
        } catch (NumberFormatException e) {
            OutputView.printExceptionMessage(e.getMessage());
            return inputManualLottoNumbers();
        }
    }

    public static int[] inputWinningNumbers() {
        OutputView.printInputWinningNumbersMessage();
        try {
            return inputIntegerArrayWithValidation();
        } catch (NumberFormatException e) {
            OutputView.printExceptionMessage(e.getMessage());
            return inputWinningNumbers();
        }
    }

    private static int[] inputIntegerArrayWithValidation() {
        try {
            return Arrays.stream(SCANNER.nextLine().split(COMMA))
                    .map(String::trim)
                    .mapToInt(Integer::parseInt)
                    .toArray();
        } catch (NumberFormatException e) {
            throw new NumberFormatException("숫자만 입력할 수 있습니다.");
        }
    }

    public static int inputBonusNumber() {
        OutputView.printInputBonusNumberMessage();
        try {
            return inputIntegerValueWithValidation();
        } catch (NumberFormatException e) {
            OutputView.printExceptionMessage(e.getMessage());
            return inputBonusNumber();
        }
    }

    private static int inputIntegerValueWithValidation() {
        try {
            String trimmedInput = SCANNER.nextLine().trim();
            return Integer.parseInt(trimmedInput);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("숫자만 입력할 수 있습니다.");
        }
    }
}
