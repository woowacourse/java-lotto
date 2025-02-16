package lotto.view;


import static lotto.common.ErrorMessage.INVALID_LOTTO_INPUT;
import static lotto.common.ErrorMessage.ONLY_LOTTO_NUMBER;
import static lotto.common.ErrorMessage.USE_SEPARATOR;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public final class InputView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String DELIMITER = ",";

    private InputView() {
    }

    public static int readMoney() {
        System.out.println("구입금액을 입력해 주세요.");

        return parseLottoNumber(scanner.nextLine());
    }

    public static List<Integer> readWinnerNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");

        return parseWinnerNumbers(scanner.nextLine());
    }

    public static int readBonusNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");

        return parseLottoNumber(scanner.nextLine());
    }

    private static int parseLottoNumber(String number) {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ONLY_LOTTO_NUMBER.getMessage());
        }
    }

    private static List<Integer> parseWinnerNumbers(String input) {
        if (input == null || input.isBlank() || input.endsWith(DELIMITER)) {
            throw new IllegalArgumentException(INVALID_LOTTO_INPUT.getMessage());
        }

        if (!input.contains(DELIMITER)) {
            throw new IllegalArgumentException(USE_SEPARATOR.getMessage());
        }

        return Arrays.stream(input.split(DELIMITER))
                .map(String::trim)
                .map(InputView::parseLottoNumber)
                .toList();
    }
}
