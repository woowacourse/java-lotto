package lotto.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static final String REQUEST_MONEY = "구입금액을 입력해 주세요.";
    private static final String REQUEST_MANUAL_COUNT = "수동으로 구매할 로또 수를 입력해 주세요.";
    private static final String REQUEST_MANUAL_LOTTO_NUMBERS = "수동으로 구매할 번호를 입력해 주세요.";
    private static final String REQUEST_WINNING_NUMBERS = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String REQUEST_BONUS_NUMBER = "보너스 볼을 입력해 주세요.";

    private static final String ERROR_NON_INTEGER_FORMAT = "숫자 형태로 입력해야 합니다.";
    private static final String ERROR_INVALID_INPUT_NUMBER_FORMAT = "구입 금액은 구분자(', ')로 구분된 숫자 형태로 입력해야 합니다.";
    private static final String ERROR_NULL_OR_EMPTY = "입력은 null 혹은 빈 문자열일 수 없습니다.";

    private static final String DELIMITER = ", ";

    private static final Scanner scanner = new Scanner(System.in);
    public static final String ERROR_NOT_POSITIVE = "음수는 입력할 수 없습니다.";

    public static int requestMoney() throws RuntimeException {
        System.out.println(REQUEST_MONEY);
        String inputMoney = scanner.nextLine();
        validateIsNullOrEmpty(inputMoney);
        return toInteger(inputMoney);
    }

    private static void validateIsNullOrEmpty(String input) {
        if (input == null || input.isEmpty()) {
            throw new RuntimeException(ERROR_NULL_OR_EMPTY);
        }
    }

    private static int toInteger(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException exception) {
            throw new RuntimeException(ERROR_NON_INTEGER_FORMAT);
        }
    }

    public static int requestManualLottoCount() throws RuntimeException {
        System.out.println(REQUEST_MANUAL_COUNT);
        String inputManualLottoCount = scanner.nextLine();
        validateIsNullOrEmpty(inputManualLottoCount);
        int intCount = toInteger(inputManualLottoCount);
        validateRange(intCount);
        return intCount;
    }

    private static void validateRange(int inputManualLottoCount) {
        if (inputManualLottoCount < 0) {
            throw new RuntimeException(ERROR_NOT_POSITIVE);
        }
    }

    public static List<List<Integer>> requestManualLottoNumbers(int count) {
        displayManualLottoNumbersGuideMsg(count);
        List<List<Integer>> manualNumbers = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            String inputManualNumbers = scanner.nextLine();
            validateIsNullOrEmpty(inputManualNumbers);
            manualNumbers.add(splitAndGetIntegerNumbers(inputManualNumbers));
        }
        return manualNumbers;
    }

    private static void displayManualLottoNumbersGuideMsg(int count) {
        if (count > 0) {
            System.out.println(REQUEST_MANUAL_LOTTO_NUMBERS);
        }
    }

    public static List<Integer> requestWinningNumbers() throws RuntimeException {
        System.out.println(REQUEST_WINNING_NUMBERS);
        String inputWinningNumbers = scanner.nextLine();
        validateIsNullOrEmpty(inputWinningNumbers);
        return splitAndGetIntegerNumbers(inputWinningNumbers);
    }

    private static List<Integer> splitAndGetIntegerNumbers(String input) throws RuntimeException {
        String[] inputNumbers = input.split(DELIMITER);
        return Arrays.stream(inputNumbers)
                .map(InputView::getIntegerFormatNumber)
                .collect(Collectors.toList());
    }

    private static int getIntegerFormatNumber(String input) {
        try {
            return toInteger(input);
        } catch (RuntimeException exception) {
            throw new RuntimeException(ERROR_INVALID_INPUT_NUMBER_FORMAT);
        }
    }

    public static int requestBonusNumber() throws RuntimeException {
        System.out.println(REQUEST_BONUS_NUMBER);
        String inputBonusNumber = scanner.nextLine();
        validateIsNullOrEmpty(inputBonusNumber);
        return toInteger(inputBonusNumber);
    }
}
