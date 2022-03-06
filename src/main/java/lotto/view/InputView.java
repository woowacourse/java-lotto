package lotto.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);
    private static final String INPUT_MONEY_MESSAGE = "구입금액을 입력해 주세요.";
    private static final String INPUT_LOTTO_COUNT_FOR_MANUAL_MESSAGE = "수동으로 구매할 로또 수를 입력해 주세요.";
    private static final String INPUT_NUMBERS_DELIMITER = ",";
    private static final String INPUT_WINNING_LOTTO_MESSAGE = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String INPUT_BONUS_NUMBER_MESSAGE = "보너스 볼을 입력해 주세요.";

    public static long inputMoney() {
        String input = "";
        boolean retryFlag = true;
        while (retryFlag) {
            System.out.println(INPUT_MONEY_MESSAGE);
            input = scanner.nextLine();
            retryFlag = validateInputMoney(input);
        }
        return Long.parseLong(input);
    }

    private static boolean validateInputMoney(String input) {
        try {
            Long.parseLong(input);
            return false;
        } catch (NumberFormatException exception) {
            OutputView.printErrorMessage(exception);
            return true;
        }
    }

    public static int inputCountOfLotto() {
        String input = "";
        boolean retryFlag = true;
        while (retryFlag) {
            OutputView.printNewLine();
            System.out.println(INPUT_LOTTO_COUNT_FOR_MANUAL_MESSAGE);
            input = scanner.nextLine();
            retryFlag = validateInputCountOfLotto(input);
        }
        return Integer.parseInt(input);
    }

    private static boolean validateInputCountOfLotto(String input) {
        try {
            Integer.parseInt(input);
            return false;
        } catch (NumberFormatException exception) {
            OutputView.printErrorMessage(exception);
            return true;
        }
    }

    public static List<Integer> inputLottoNumbers() {
        String input = "";
        boolean retryFlag = true;
        while (retryFlag) {
            input = scanner.nextLine();
            retryFlag = validateInputLottoNumbers(input);
        }
        return splitAndParseInteger(input);
    }

    private static boolean validateInputLottoNumbers(String input) {
        try {
            splitAndParseInteger(input);
            return false;
        } catch (NumberFormatException exception) {
            OutputView.printErrorMessage(exception);
            return true;
        }
    }

    private static List<Integer> splitAndParseInteger(String input) {
        return Arrays.stream(input.split(INPUT_NUMBERS_DELIMITER))
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    public static List<Integer> inputWinningLotto() {
        String input = "";
        boolean retryFlag = true;
        while (retryFlag) {
            OutputView.printNewLine();
            System.out.println(INPUT_WINNING_LOTTO_MESSAGE);
            input = scanner.nextLine();
            retryFlag = validateInputWinningLotto(input);
        }
        return splitAndParseInteger(input);
    }

    private static boolean validateInputWinningLotto(String input) {
        try {
            splitAndParseInteger(input);
            return false;
        } catch (NumberFormatException exception) {
            OutputView.printErrorMessage(exception);
            return true;
        }
    }

    public static int inputBonusNumber() {
        String input = "";
        boolean retryFlag = true;
        while (retryFlag) {
            System.out.println(INPUT_BONUS_NUMBER_MESSAGE);
            input = scanner.nextLine();
            retryFlag = validateInputBonusNumber(input);
        }
        return Integer.parseInt(input);
    }

    private static boolean validateInputBonusNumber(String input) {
        try {
            Integer.parseInt(input);
            return false;
        } catch (NumberFormatException exception) {
            OutputView.printErrorMessage(exception);
            return true;
        }
    }
}
