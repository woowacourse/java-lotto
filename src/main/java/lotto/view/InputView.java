package lotto.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);
    private static final String INPUT_WINNING_NUMBER_MESSAGE = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String INPUT_INTEGER_ERROR_MESSAGE = "입력은 정수여야 합니다.";
    private static final String NUMBER_DELIMITER = ",";

    private InputView() {
    }

    public static int getInt() {
        try {
            String input = scanner.nextLine()
                                  .trim();
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INPUT_INTEGER_ERROR_MESSAGE);
        }
    }

    public static List<Integer> getWinningNumbers() {
        try {
            OutputView.getMessage(INPUT_WINNING_NUMBER_MESSAGE);
            String input = scanner.nextLine()
                                  .trim();
            return Arrays.stream(input.split(NUMBER_DELIMITER))
                         .map(String::trim)
                         .map(Integer::new)
                         .collect(Collectors.toList());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INPUT_INTEGER_ERROR_MESSAGE);
        }
    }

}
