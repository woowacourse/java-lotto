package model;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LottoWinningNumber {
    private static final String REGEX_NUMBER = "[0-9]+";
    private static final int LOTTO_MIN_NUMBER = 1;
    private static final int LOTTO_MAX_NUMBER = 45;
    private static final String WINNING_NUMBER_ERROR_MESSAGE = "[Error]: 당첨 번호는 숫자여야 합니다.";
    private static final String WINNING_NUMBER_BLANK_ERROR_MESSAGE = "[Error]: 당첨 번호를 입력하세요.";
    private static final String WINNING_NUMBER_RANGE_ERROR_MESSAGE = "[Error]: 당첨 번호는 1~45 숫자여야 합니다.";
    private static final String WINNING_NUMBER_SIZE_ERROR_MESSAGE = "[Error]: 당첨 번호는 6개의 숫자여야 합니다.";
    private static final int WINNING_NUMBER_SIZE = 6;
    private static final String WINNING_NUMBER_REDUPLICATION_ERROR_MESSAGE = "[Error]: 당첨 번호는 중복이 있으면 안됩니다";
    private static final String REDUPLICATION_WITH_BONUS_BALL_ERROR_MESSAGE = "[Error]: 당첨 번호와 보너스 볼이 중복됩니다.";

    private final List<Integer> winningNumbers;

    public LottoWinningNumber(String numbers) {
        validateInputNumbersBlank(numbers);
        validateNumbersConsistOfInt(numbers);
        validateNumberOutOfRange(numbers);
        validateNumberSize(numbers);
        validateNumberReduplication(numbers);
        this.winningNumbers = makeWinningNumbers(split(numbers));
    }

    private void validateInputNumbersBlank(String number) {
        if (number == null || number.isBlank()) {
            throw new IllegalArgumentException(WINNING_NUMBER_BLANK_ERROR_MESSAGE);
        }
    }

    private List<String> split(String numbers) {
        return Arrays.asList(numbers.replace(" ", "").split(","));
    }

    private void validateNumbersConsistOfInt(String numbers) {
        String joinNumbers = String.join("", split(numbers));

        if (!joinNumbers.matches(REGEX_NUMBER)) {
            throw new IllegalArgumentException(WINNING_NUMBER_ERROR_MESSAGE);
        }
    }

    private void validateNumberOutOfRange(String numbers) {
        long count = split(numbers).stream()
                .filter(number -> Integer.parseInt(number) < LOTTO_MIN_NUMBER || Integer.parseInt(number) > LOTTO_MAX_NUMBER)
                .count();

        if (count > 0) {
            throw new IllegalArgumentException(WINNING_NUMBER_RANGE_ERROR_MESSAGE);
        }
    }

    private void validateNumberSize(String numbers) {
        if (split(numbers).size() != WINNING_NUMBER_SIZE) {
            throw new IllegalArgumentException(WINNING_NUMBER_SIZE_ERROR_MESSAGE);
        }
    }

    private void validateNumberReduplication(String numbers) {
        long count = split(numbers).stream().distinct().count();

        if (count != split(numbers).size()) {
            throw new IllegalArgumentException(WINNING_NUMBER_REDUPLICATION_ERROR_MESSAGE);
        }
    }

    private List<Integer> makeWinningNumbers(List<String> numbers) {
        return numbers.stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    public void validateReduplicationWithBonusBall(String number) {
        if (winningNumbers.contains(Integer.parseInt(number))) {
            throw new IllegalArgumentException(REDUPLICATION_WITH_BONUS_BALL_ERROR_MESSAGE);
        }
    }

    public List<Integer> getWinningNumbers() {
        return winningNumbers;
    }
}
