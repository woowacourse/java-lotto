package lotto.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static final String ERROR_NON_INTEGER_FORMAT = "구입 금액은 숫자 형태로 입력해야 합니다.";
    private static final String ERROR_NULL_OR_EMPTY = "입력 null 혹은 빈 문자열 일수 없습니다.";
    private static final String REQUEST_MONEY = "구입금액을 입력해 주세요.";
    private static final String REQUEST_WINNING_NUMBERS = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String REQUEST_BONUS_NUMBER = "보너스 볼을 입력해 주세요.";

    private static final Scanner scanner = new Scanner(System.in);

    public static int requestMoney() {
        System.out.println(REQUEST_MONEY);
        String inputMoney = scanner.nextLine();
        validateIsNullOrEmpty(inputMoney);
        return toInteger(inputMoney);
    }

    public static List<Integer> requestWinningNumbers() {
        System.out.println(REQUEST_WINNING_NUMBERS);
        String inputWinningNumbers = scanner.nextLine();
        validateIsNullOrEmpty(inputWinningNumbers);
        return splitNumbers(inputWinningNumbers);
    }

    public static int requestBonusNumber() {
        System.out.println(REQUEST_BONUS_NUMBER);
        String inputBonusNumber = scanner.nextLine();
        validateIsNullOrEmpty(inputBonusNumber);
        return toInteger(inputBonusNumber);
    }

    public static List<Integer> splitNumbers(String input) {
        String[] inputNumbers = input.split(", ");
        return Arrays.stream(inputNumbers)
                .map(InputView::toInteger)
                .collect(Collectors.toList());
    }

    public static int toInteger(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException numberFormatException) {
            throw new RuntimeException(ERROR_NON_INTEGER_FORMAT);
        }
    }

    public static void validateIsNullOrEmpty(String input) {
        if (input == null || input.isEmpty()) {
            throw new RuntimeException(ERROR_NULL_OR_EMPTY);
        }
    }
}
