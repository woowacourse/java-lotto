package domain;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class WinningNumbers {

    private static final int LOTTO_SIZE = 6;
    private static final String LOTTO_NUMBER_DELIMITER = ", |,";
    private static final String ERROR_DUPLICATE_NUMBER = "로또 번호는 중복되지 않은 6자리 숫자여야 합니다.";
    private static final String ERROR_DUPLICATE_BONUS = "보너스 번호가 당첨 번호와 중복됩니다.";
    private static final String ERROR_NUM_OF_BALLS = "로또 번호는 6개의 번호를 입력해줘야 합니다.";
    private static final String ERROR_BALL_NON_INTEGER = "로또 번호는 숫자만 입력해줘야 합니다.";

    private final Set<Number> winningNumbers;
    private Number bonus;

    public WinningNumbers(String inputNumbers, String bonusNumber) {
        Set<Integer> numbers = validateWinningNumber(inputNumbers);
        checkRightSize(numbers);
        winningNumbers = new HashSet<>();
        for (Integer number : numbers) {
            winningNumbers.add(new Number(number));
        }
        bonus = validateBonusNumber(bonusNumber);
    }

    private static Set<Integer> validateWinningNumber(final String inputNumbers) {
        final List<String> splitNumbers = Arrays.asList(inputNumbers.split(LOTTO_NUMBER_DELIMITER));
        checkNumOfBalls(splitNumbers);

        return checkNonIntegers(splitNumbers);
    }

    private static void checkNumOfBalls(List<String> numbers) {
        if (numbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException(ERROR_NUM_OF_BALLS);
        }
    }

    private static Set<Integer> checkNonIntegers(List<String> numbers) {
        return numbers.stream()
                .map(number -> checkNonInteger(number))
                .collect(Collectors.toSet());
    }

    private static int checkNonInteger(final String number) {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_BALL_NON_INTEGER);
        }
    }

    private void checkRightSize(final Set<Integer> numbers) {
        if (numbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException(ERROR_DUPLICATE_NUMBER);
        }
    }

    private Number validateBonusNumber(final String number) {
        final Number bonusNumber = new Number(checkNonInteger(number));
        if (winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException(ERROR_DUPLICATE_BONUS);
        }
        return bonusNumber;
    }

    public Set<Number> getWinningNumbers() {
        return winningNumbers;
    }

    public Number getBonus() {
        return bonus;
    }

}
