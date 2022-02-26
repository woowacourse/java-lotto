package lotterymachine.view;

import lotterymachine.utils.LotteryRule;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static lotterymachine.utils.LotteryRule.*;
import static lotterymachine.view.ErrorMessage.*;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String NUMBER_DELIMITER = ",";

    public static int getAmount() {
        System.out.println("구입금액을 입력해 주세요.");
        try {
            return toInt(scanner.nextLine());
        } catch (RuntimeException runtimeException) {
            System.out.println(runtimeException.getMessage());
            return getAmount();
        }
    }

    public static List<Integer> getWinningLotteryNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        try {
            String[] input = scanner.nextLine().split(NUMBER_DELIMITER);
            List<Integer> numbers = toIntegers(input);
            validateWinningLotteryNumbers(numbers);
            return numbers;
        } catch (RuntimeException runtimeException) {
            System.out.println(runtimeException.getMessage());
            return getWinningLotteryNumbers();
        }
    }

    public static int getBonusNumber(List<Integer> numbers) {
        System.out.println("보너스 볼을 입력해 주세요.");
        try {
            int bonusNumber = toInt(scanner.nextLine());
            validateBonusNumber(numbers, bonusNumber);
            return bonusNumber;
        } catch (RuntimeException runtimeException) {
            System.out.println(runtimeException.getMessage());
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
            throw new RuntimeException(IS_NOT_NUMBER.getMessage());
        }
    }

    private static List<Integer> toIntegers(String[] input) {
        return Arrays.stream(input)
                .map(String::trim)
                .map(InputView::toInt)
                .collect(Collectors.toList());
    }

    private static void validateWinningLotteryNumbers(List<Integer> numbers) {
        validateNumbers(numbers);
        validateSize(numbers);
        validateDuplication(numbers);
    }

    private static void validateNumbers(List<Integer> numbers) {
        numbers.stream()
                .filter(LotteryRule::isRangeOfNumber)
                .findFirst()
                .orElseThrow(() -> new RuntimeException(OUT_OF_RANGE.getMessage()));
    }

    private static void validateSize(List<Integer> numbers) {
        if (numbers.size() != TICKET_SIZE.getNumber()) {
            throw new RuntimeException(INVALID_SIZE.getMessage());
        }
    }

    private static void validateDuplication(List<Integer> input) {
        if (input.stream().distinct().count() != input.size()) {
            throw new RuntimeException(DUPLICATE_NUMBER.getMessage());
        }
    }
}
