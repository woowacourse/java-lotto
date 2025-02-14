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

    public Lotto(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public Lotto(String lotto) {
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

    public boolean isContains(int num) {
        return numbers.contains(num);
    }

    public int matchCount(WinningLotto winningLotto) {
        return (int) numbers.stream()
                .filter(winningLotto::isContains)
                .count();
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    private void validateLottoDuplicate() {
        if (numbers.stream().distinct().count() != LOTTO_LENGTH) {
            throw new IllegalArgumentException(DUPLICATED_NUMBER.getMessage());
        }
    }

    private void validateLength(String[] splitNumbers) {
        if (splitNumbers.length != LOTTO_LENGTH) {
            throw new IllegalArgumentException(INVALID_FORMAT.getMessage());
        }
    }

    private void validateRange(int num) {
        if (num < LOTTO_MIN || num > LOTTO_MAX) {
            throw new IllegalArgumentException(INVALID_RANGE.getMessage());
        }
    }

    private int validateIsInteger(String s) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_FORMAT.getMessage());
        }
    }
}
