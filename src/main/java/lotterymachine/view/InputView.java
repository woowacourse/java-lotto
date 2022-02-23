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
        } catch (NumberFormatException numberFormatException) {
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

    private static int toInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException exception) {
            System.out.println(IS_NOT_NUMBER.getMessage());
            throw exception;
        }
    }

    private static List<Integer> toIntegers(String[] input) {
        return Arrays.stream(input)
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
                .filter(LotteryRule::checkRange)
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
