package lotto.view;

import java.util.*;
import java.util.stream.Collectors;

public class InputView {
    public static String takeMoneyInput(Scanner scanner) {
        System.out.println("구입금액을 입력해 주세요");
        String money = scanner.nextLine();

        return money;
    }

    public static List<Integer> winningNumbers(Scanner scanner) {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        String winningNumberInput = scanner.nextLine();
        String[] numbers = winningNumberInput.split(", ");

        for (String number : numbers) {
            validateNumber(number);
            validateNumberBetween1to45(number);
        }
        validateSixNumbers(numbers);
        validateDuplicates(numbers);
        return Arrays.stream(numbers)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    private static void validateDuplicates(String[] numbers) {
        Set<String> numberGroup = new HashSet<>(Arrays.asList(numbers));
        if (numberGroup.size() != 6) {
            throw new IllegalArgumentException("중복되는 번호는 안됩니다.");
        }
    }

    private static void validateSixNumbers(String[] numbers) {
        if (numbers.length != 6) {
            throw new IllegalArgumentException("당첨 번호는 총 6개 이어야 합니다.");
        }
    }

    private static void validateNumberBetween1to45(String number) {
        if (Integer.parseInt(number) < 1 && 45 < Integer.parseInt(number)) {
            throw new IllegalArgumentException("당첨 번호는 1 에서 45 사이 여야 합니다.");
        }
    }

    private static void validateNumber(String number) {
        try {
            Integer.parseInt(number);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("당첨 번호는 숫자여야 합니다.");
        }
    }

    public static int bonusNumber() {
        return 0;
    }
}
