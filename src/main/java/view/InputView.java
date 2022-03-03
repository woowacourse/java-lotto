package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);

    private static final String NUMBERS_INPUT_FORMAT_REGEX = ".(.)?, .(.)?, .(.)?, .(.)?, .(.)?, .(.)?";
    private static final String SPLIT_DELIMITER = ", ";

    private static final String ERROR_MESSAGE_FOR_INVALID_NUMBER = "숫자만 입력해주세요.";
    private static final String ERROR_MESSAGE_FOR_INVALID_NUMBERS_INPUT_FORMAT = "잘못된 숫자 입력 형식입니다. '1, 2, 3, 4, 5, 6' 의 형태로 입력해주세요.";
    private static final String ERROR_MESSAGE_FOR_EMPTY_INPUT = "빈 문자열을 입력할 수 없습니다.";

    private static final String MESSAGE_TO_GET_INPUT_MONEY = "구입금액을 입력해 주세요.";
    private static final String MESSAGE_FOR_MANUAL_LOTTO_QUANTITY = "수동으로 구매할 로또 수를 입력해 주세요.";
    private static final String MESSAGE_FOR_MANUAL_LOTTO_NUMBERS = "수동으로 구매할 번호를 입력해 주세요.";
    private static final String MESSAGE_FOR_WINNING_LOTTO_NUMBERS = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String MESSAGE_FOR_BONUS_NUMBER = "보너스 볼을 입력해 주세요.";

    public static int scanInputMoney() {
        System.out.println(MESSAGE_TO_GET_INPUT_MONEY);
        return scanNumber();
    }

    public static int scanManualLottoQuantity() {
        System.out.println(System.lineSeparator() + MESSAGE_FOR_MANUAL_LOTTO_QUANTITY);
        return scanNumber();
    }

    public static List<Set<Integer>> scanManualLottoNumbers(int quantity) {
        System.out.println(System.lineSeparator() + MESSAGE_FOR_MANUAL_LOTTO_NUMBERS);

        return IntStream.range(0, quantity)
                .mapToObj(i -> scanLottoNumbers())
                .collect(Collectors.toList());
    }

    public static Set<Integer> scanWinningLottoNumbers() {
        System.out.println(System.lineSeparator() + MESSAGE_FOR_WINNING_LOTTO_NUMBERS);
        return scanLottoNumbers();
    }

    public static int scanBonusNumber() {
        System.out.println(MESSAGE_FOR_BONUS_NUMBER);
        return scanNumber();
    }

    private static int scanNumber() {
        String input = scanInput();

        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(ERROR_MESSAGE_FOR_INVALID_NUMBER);
        }
    }

    private static Set<Integer> scanLottoNumbers() {
        String input = scanInput();
        validateNumbersInputFormat(input);

        try {
            return generateLottoNumbersFromInput(input);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(ERROR_MESSAGE_FOR_INVALID_NUMBER);
        }
    }

    private static void validateNumbersInputFormat(String input) {
        if (!input.matches(NUMBERS_INPUT_FORMAT_REGEX)) {
            throw new IllegalArgumentException(ERROR_MESSAGE_FOR_INVALID_NUMBERS_INPUT_FORMAT);
        }
    }

    private static String scanInput() {
        String input = SCANNER.nextLine();
        validateEmpty(input);

        return input;
    }

    private static void validateEmpty(String input) {
        if (input.isEmpty()) {
            throw new IllegalArgumentException(ERROR_MESSAGE_FOR_EMPTY_INPUT);
        }
    }

    private static Set<Integer> generateLottoNumbersFromInput(String input) {
        return Arrays.stream(input.split(SPLIT_DELIMITER))
                .map(Integer::parseInt)
                .collect(Collectors.toSet());
    }
}
