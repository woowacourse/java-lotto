package domain;

import static global.exception.ExceptionMessage.DUPLICATED_NUMBER;
import static global.exception.ExceptionMessage.INVALID_FORMAT;
import static global.exception.ExceptionMessage.INVALID_RANGE;

import java.util.ArrayList;
import java.util.List;

public class Lotto {
    public static final int LOTTO_MIN = 1;
    public static final int LOTTO_MAX = 45;
    public static final String DELIMITER = ",";
    public static final int LOTTO_LENGTH = 6;

    protected List<Integer> numbers;

    public Lotto(final List<Integer> numbers) {
        this.numbers = numbers;
    }

    public Lotto(final String lotto) {
        numbers = new ArrayList<>();
        String[] splitNumbers = lotto.split(DELIMITER);

        validateLength(splitNumbers);
        for (String number : splitNumbers) {
            int num = validateIsInteger(number.trim());
            validateRange(num);
            numbers.add(num);
        }
        validateLottoDuplicate();
    }

    public boolean contains(final int num) {
        return numbers.contains(num);
    }

    public int matchCount(final WinningLotto winningLotto) {
        return (int) numbers.stream()
                .filter(winningLotto::contains)
                .count();
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    private void validateLottoDuplicate() {
        int count = (int) numbers.stream()
                .distinct()
                .count();

        if (count != LOTTO_LENGTH) {
            throw new IllegalArgumentException(DUPLICATED_NUMBER.getMessage());
        }
    }

    private void validateLength(final String[] splitNumbers) {
        if (splitNumbers.length != LOTTO_LENGTH) {
            throw new IllegalArgumentException(INVALID_FORMAT.getMessage());
        }
    }

    private void validateRange(final int num) {
        if (num < LOTTO_MIN || num > LOTTO_MAX) {
            throw new IllegalArgumentException(INVALID_RANGE.getMessage());
        }
    }

    private int validateIsInteger(final String inputNumber) {
        try {
            return Integer.parseInt(inputNumber);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_FORMAT.getMessage());
        }
    }
}
