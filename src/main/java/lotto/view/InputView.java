package lotto.view;

import java.util.Scanner;

public class InputView {
    private static final String INPUT_AMOUNT_MESSAGE = "구입금액을 입력해 주세요.";
    private static final String INPUT_WINNING_NUMBERS_MESSAGE = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String INPUT_BONUS_BALL_MESSAGE = "보너스 볼을 입력해 주세요.";
    private static final String LOTTO_ERROR_PREFIX = "로또 번호는";
    private static final String NOT_NUMBER_ERROR = "숫자로 입력해주세요.";
    public static final String NOT_INSTANTIATION_ERROR = "InputView 객체를 생성할 수 없습니다.";
    private static final String EMPTY = "";
    private static final String BLANK = " ";
    public static final String BLANK_AND_DELIMITER_REGEX = "[\\s,]*";

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
        String input = scanner.nextLine();

        return parseWinningNumbers(input);
    }

    private static String parseWinningNumbers(String input) {
        String parsedInput = input.replaceAll(BLANK_AND_DELIMITER_REGEX, EMPTY);
        try {
            Integer.parseInt(parsedInput);
            return input;
        } catch (NumberFormatException e) {
            System.out.println(LOTTO_ERROR_PREFIX + BLANK + NOT_NUMBER_ERROR);
            return inputWinningNumbers();
        }
    }

    public static int inputBonusBall() {
        System.out.println(INPUT_BONUS_BALL_MESSAGE);
        return convertToInt(scanner.nextLine(), NOT_NUMBER_ERROR);
    }
}
