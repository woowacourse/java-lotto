package lotto.view;

import java.util.Arrays;
import java.util.Scanner;

public class InputView {
    private static final String INPUT_AMOUNT_MESSAGE = "구입금액을 입력해 주세요.";
    private static final String INPUT_WINNING_NUMBERS_MESSAGE = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String INPUT_BONUS_BALL_MESSAGE = "보너스 볼을 입력해 주세요.";
    private static final String LOTTO_ERROR_PREFIX = "로또 번호는";
    private static final String NOT_NUMBER_ERROR = "숫자로 입력해주세요.";
    private static final String NOT_INSTANTIATION_ERROR = "InputView 객체를 생성할 수 없습니다.";
    private static final String EMPTY = "";
    private static final String BLANK = " ";
    private static final String DELIMITER = ",";

    private static final Scanner scanner = new Scanner(System.in);

    private InputView() throws InstantiationException {
        throw new InstantiationException(NOT_INSTANTIATION_ERROR);
    }

    public static int inputAmount() {
        System.out.println(INPUT_AMOUNT_MESSAGE);
        return convertToInt(scanner.nextLine(), NOT_NUMBER_ERROR);
    }

    private static int convertToInt(String input, String errorMessage) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println(errorMessage);
            return convertToInt(scanner.nextLine(), errorMessage);
        }
    }

    public static String inputWinningNumbers() {
        System.out.println(INPUT_WINNING_NUMBERS_MESSAGE);
        String inputRemovedBlank = scanner.nextLine().replaceAll(BLANK, EMPTY);
        if (isNumber(inputRemovedBlank)) {
            return inputRemovedBlank;
        }
        return inputWinningNumbers();
    }

    private static boolean isNumber(String input) {
        String[] splitInput = input.split(DELIMITER);

        try {
            Arrays.stream(splitInput).forEach(Integer::parseInt);
            return true;
        } catch (NumberFormatException e) {
            System.out.println(LOTTO_ERROR_PREFIX + BLANK + NOT_NUMBER_ERROR);
            return false;
        }
    }

    public static int inputBonusBall() {
        System.out.println(INPUT_BONUS_BALL_MESSAGE);
        return convertToInt(scanner.nextLine(), NOT_NUMBER_ERROR);
    }
}
