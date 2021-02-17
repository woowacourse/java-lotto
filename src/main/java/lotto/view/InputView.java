package lotto.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static Scanner scanner = new Scanner(System.in);
    private static final String INPUT_MONEY_MESSAGE = "구입금액을 입력해 주세요.";
    private static final String INPUT_INTEGER_ERROR_MESSAGE = "입력은 숫자여야 합니다.";
    private static final String INPUT_WINNING_NUMBER_MESSAGE = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String NUMBER_DELIMITER = ",";
    private static final String INPUT_BONUS_BALL_MESSAGE = "보너스 볼을 입력해 주세요.";

    private InputView() {
    }

    public static int getInputMoney() {
        try {
            OutputView.getMessage(INPUT_MONEY_MESSAGE);
            String input = scanner.nextLine().trim();
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INPUT_INTEGER_ERROR_MESSAGE);
        }
    }

    public static List<Integer> getWinningNumbers() {
        try {
            OutputView.getMessage(INPUT_WINNING_NUMBER_MESSAGE);
            String input = scanner.nextLine().trim();
            return Arrays.stream(input.split(NUMBER_DELIMITER))
                .map(String::trim)
                .map(Integer::new)
                .collect(Collectors.toList());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INPUT_INTEGER_ERROR_MESSAGE);
        }
    }

    public static int getBonusBall() {
        try {
            OutputView.getMessage(INPUT_BONUS_BALL_MESSAGE);
            String input = scanner.nextLine().trim();
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INPUT_INTEGER_ERROR_MESSAGE);
        }
    }
}
