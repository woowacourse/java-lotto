package lotto.view;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class InputView {

    private static final String LOTTO_NUMBER_DELIMITER = ",";
    private static final int MINIMUM_AMOUNT = 0;

    private static final Scanner SCANNER = new Scanner(System.in);

    public static int inputMoney() {
        System.out.println("구입금액을 입력해 주세요.");
        return stringToInt(validateBlank(SCANNER.nextLine()));
    }

    public static int inputManualLottoAmount() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        return stringToInt(validateBlank(SCANNER.nextLine()));
    }

    public static List<List<Integer>> inputManualLottoNumbers(int amount) {
        if (amount > MINIMUM_AMOUNT) {
            System.out.println("수동으로 구매할 번호를 입력해 주세요.");

            return IntStream.range(0, amount)
                .mapToObj(i -> convertToNumbers(validateBlank(SCANNER.nextLine())))
                .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    public static List<Integer> inputWinnerNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        return convertToNumbers(validateBlank(SCANNER.nextLine()));
    }

    public static int inputBonusNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");
        return stringToInt(validateBlank(SCANNER.nextLine()));
    }

    private static List<Integer> convertToNumbers(String input) {
        return Arrays.stream(input.split(LOTTO_NUMBER_DELIMITER))
            .map(number -> stringToInt(number.trim()))
            .collect(Collectors.toList());
    }

    private static String validateBlank(String input) {
        if (Objects.isNull(input) || input.isBlank()) {
            throw new IllegalArgumentException("입력값은 비어있을 수 없다.");
        }
        return input;
    }

    private static int stringToInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("올바른 숫자를 입력해야 한다.");
        }
    }
}
