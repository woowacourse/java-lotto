package lotto.view;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;
import lotto.domain.vo.Money;
import lotto.domain.vo.Number;

public class InputView {

    private static final String DELIMITER = ",";

    private static final Scanner SCANNER = new Scanner(System.in);

    public static Money inputMoney() {
        System.out.println("구입금액을 입력해 주세요.");
        return new Money(validateNumber(validateBlank(SCANNER.nextLine())));
    }

    public static List<Number> inputWinnerNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        return convertToNumbers(validateBlank(SCANNER.nextLine()));
    }

    public static Number inputBonusNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");
        return new Number(validateNumber(validateBlank(SCANNER.nextLine())));
    }

    private static List<Number> convertToNumbers(String input) {
        return Arrays.stream(input.split(DELIMITER))
            .map(String::trim)
            .map(InputView::validateNumber)
            .map(Number::new)
            .collect(Collectors.toList());
    }

    private static String validateBlank(String input) {
        if (Objects.isNull(input) || input.isBlank()) {
            throw new IllegalArgumentException("입력값은 비어있을 수 없다.");
        }
        return input;
    }

    private static int validateNumber(String input) {
        if (isNumeric(input)) {
            return Integer.parseInt(input);
        }
        throw new IllegalArgumentException("구입금액은 숫자이어야 한다.");
    }

    private static boolean isNumeric(String value) {
        return value.chars().allMatch(Character::isDigit);
    }
}
