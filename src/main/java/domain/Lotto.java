package domain;

import static global.exception.ExceptionMessage.DUPLICATED_NUMBER;
import static global.exception.ExceptionMessage.INVALID_FORMAT;
import static global.exception.ExceptionMessage.INVALID_RANGE;

import java.util.ArrayList;
import java.util.List;

public class Lotto {
    private static final int LOTTO_MIN = 1;
    private static final int LOTTO_MAX = 45;
    private static final String DELIMITER = ",";
    private static final int LOTTO_LENGTH = 6;

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

    public boolean contains(final int number) {
        return numbers.contains(number);
    }

    public int matchCount(final WinningLotto winningLotto) {
        return (int) numbers.stream()
                .filter(winningLotto::contains)
                .count();
    }

    public List<Integer> getNumbers() {
        return new ArrayList<>(numbers);
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

    private void validateRange(final int number) {
        if (number < LOTTO_MIN || number > LOTTO_MAX) {
            throw new IllegalArgumentException(INVALID_RANGE.getMessage());
        }
    }

    private int validateIsInteger(final String number) {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_FORMAT.getMessage());
        }
    }
}
