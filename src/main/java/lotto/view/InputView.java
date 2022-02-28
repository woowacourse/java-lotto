package lotto.view;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static final String INPUT_MONEY_MESSAGE = "구입금액을 입력해 주세요.";
    private static final String ERROR_NO_INPUT_MESSAGE = "입력이 없습니다.";
    private static final String ERROR_INPUT_EMPTY_MESSAGE = "공백 또는 빈 문자열이 입력하면 안됩니다.";
    private static final String ERROR_NOT_NUMBER_MESSAGE = "숫자 이외의 문자가 입력하면 안됩니다.";
    private static final String INPUT_WINNING_NUMBER_MESSAGE = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String INPUT_BONUS_NUMBER_MESSAGE = "보너스 볼을 입력해 주세요.";
    private static final String INPUT_WINNING_DELIMITER = ",";

    private static final Scanner scanner = new Scanner(System.in);

    public static int requestPurchaseMoney() {
        System.out.println(INPUT_MONEY_MESSAGE);
        String input = nextLine();
        validateEmpty(input);
        return toInt(input);
    }

    private static String nextLine() {
        String input;
        try {
            input = scanner.nextLine();
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException(ERROR_NO_INPUT_MESSAGE);
        }
        return input;
    }

    private static void validateEmpty(String input) {
        if (input.isEmpty()) {
            throw new IllegalArgumentException(ERROR_INPUT_EMPTY_MESSAGE);
        }
    }

    private static int toInt(String money) {
        try {
            return Integer.parseInt(money);
        } catch (NumberFormatException e) {
            throw new NumberFormatException(ERROR_NOT_NUMBER_MESSAGE);
        }
    }

    public static List<Integer> requestWinningNumber() {
        System.out.println(INPUT_WINNING_NUMBER_MESSAGE);
        String input = nextLine();

        return Arrays.stream(input.split(INPUT_WINNING_DELIMITER))
                .map(value -> toInt(value.trim()))
                .collect(Collectors.toList());
    }

    public static Integer requestBonusNumber() {
        System.out.println(INPUT_BONUS_NUMBER_MESSAGE);
        Integer bonusNumber = toInt(nextLine());
        System.out.println();
        return bonusNumber;
    }
}
