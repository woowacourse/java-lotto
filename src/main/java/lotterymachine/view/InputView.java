package lotterymachine.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    private static final String NUMBER_DELIMITER = ",";
    private static final String IS_NOT_NUMBER_EXCEPTION = "숫자만 입력할 수 있습니다.";
    private static final String DUPLICATE_BONUS_NUMBER_EXCEPTION = "보너스 볼이 당첨 번호와 중복됩니다.";

    public static int getAmount() {
        System.out.println("구입금액을 입력해 주세요.");
        return toInt(scanner.nextLine());
    }

    public static int getNumberOfManualTickets() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        return toInt(scanner.nextLine());
    }

    public static List<Integer> getManualTicket() {
        String[] input = scanner.nextLine().split(NUMBER_DELIMITER);
        return toIntegers(input);
    }

    public static List<Integer> getWinningNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        String[] input = scanner.nextLine().split(NUMBER_DELIMITER);
        return toIntegers(input);
    }

    public static int getBonusNumber(List<Integer> numbers) {
        System.out.println("보너스 볼을 입력해 주세요.");
        try {
            int bonusNumber = toInt(scanner.nextLine());
            validateBonusNumber(numbers, bonusNumber);
            return bonusNumber;
        } catch (RuntimeException runtimeException) {
            OutputView.printException(runtimeException.getMessage());
            return getBonusNumber(numbers);
        }
    }

    private static void validateBonusNumber(List<Integer> numbers, int bonusNumber) {
        if (numbers.contains(bonusNumber)) {
            throw new IllegalArgumentException(DUPLICATE_BONUS_NUMBER_EXCEPTION);
        }
    }

    private static int toInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (RuntimeException runtimeException) {
            throw new IllegalArgumentException(IS_NOT_NUMBER_EXCEPTION);
        }
    }

    private static List<Integer> toIntegers(String[] input) {
        return Arrays.stream(input)
                .map(String::trim)
                .map(InputView::toInt)
                .collect(Collectors.toList());
    }
}
