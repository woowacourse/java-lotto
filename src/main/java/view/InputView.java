package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String DELIMITER = ", ";

    public static int requestAmount() {
        System.out.println("구입금액을 입력해 주세요.");
        return getIntegerInput();
    }

    private static int getIntegerInput() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("숫자를 입력해 주세요");
            return getIntegerInput();
        }
    }

    public static List<Integer> requestWinNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        return getLottoNumbers();
    }

    public static List<Integer> getLottoNumbers() {
        String numbersInput = scanner.nextLine();
        checkEmpty(numbersInput);
        return toInteger(toList(numbersInput));
    }

    private static void checkEmpty(String numbers) {
        if (numbers == null || numbers.isBlank()) {
            throw new IllegalArgumentException("빈 문자를 입력할 수 없습니다.");
        }
    }

    private static List<String> toList(String numberInput) {
        return List.of(numberInput.split(DELIMITER, -1));
    }

    private static List<Integer> toInteger(List<String> numbers) {
        try {
            return numbers.stream()
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException("숫자를 입력해주세요.");
        }
    }

    public static int requestBonusNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");
        return getIntegerInput();
    }

    public static int requestManualTicketCount() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        return getIntegerInput();
    }

    public static List<List<Integer>> requestManualNumbers(int manualTicketsCount) {
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
        List<List<Integer>> inputs = new ArrayList<>();
        for (int count = 0; count < manualTicketsCount; count++) {
            inputs.add(getLottoNumbers());
        }
        return inputs;
    }
}
