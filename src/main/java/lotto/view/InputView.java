package lotto.view;

import static java.util.stream.Collectors.*;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final String INPUT_MONEY_MESSAGE = "구입금액을 입력해 주세요.";
    private static final String INPUT_NORMAL_WINNING_NUMBER_MESSAGE = "\n지난 주 당첨 번호를 입력해 주세요.";
    private static final String SPLIT_REGEX = ", ";
    private static final int SPLIT_LIMIT = -1;
    private static final String WINNING_NUMBER_NUMBER_FORMAT_ERROR_MESSAGE = "당첨 번호는 정수만 가능합니다.";
    private static final String INPUT_BONUS_NUMBER_MESSAGE = "보너스 볼을 입력해 주세요.";

    private final Scanner scanner;

    public InputView() {
        this.scanner = new Scanner(System.in);
    }

    public int getMoney() {
        System.out.println(INPUT_MONEY_MESSAGE);
        String input = scanner.nextLine();
        return parseInt(input);
    }

    public List<Integer> getNormalNumbers() {
        System.out.println(INPUT_NORMAL_WINNING_NUMBER_MESSAGE);
        String[] splitWinningNumbers = scanner.nextLine().split(SPLIT_REGEX, SPLIT_LIMIT);

        return Arrays.stream(splitWinningNumbers)
                .map(this::parseInt)
                .collect(toList());
    }

    public int getBonusNumber() {
        System.out.println(INPUT_BONUS_NUMBER_MESSAGE);
        String input = scanner.nextLine();
        return parseInt(input);
    }

    private int parseInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(WINNING_NUMBER_NUMBER_FORMAT_ERROR_MESSAGE);
        }
    }
}
