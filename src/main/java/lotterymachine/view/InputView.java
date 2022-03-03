package lotterymachine.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static lotterymachine.model.ErrorMessage.*;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String NUMBER_DELIMITER = ",";

    public static int getAmount() {
        System.out.println("구입금액을 입력해 주세요.");
        return toInt(scanner.nextLine());
    }

    public static int getNumberOfManualTickets() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        return toInt(scanner.nextLine());
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
            throw new RuntimeException(DUPLICATE_BONUS_NUMBER.getMessage());
        }
    }

    private static int toInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (RuntimeException runtimeException) {
            throw new IllegalArgumentException(IS_NOT_NUMBER.getMessage());
        }
    }

    private static List<Integer> toIntegers(String[] input) {
        return Arrays.stream(input)
                .map(String::trim)
                .map(InputView::toInt)
                .collect(Collectors.toList());
    }
}
