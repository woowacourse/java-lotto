package domain;

import exception.AppException;

import java.util.List;

public class Lotto {

    public static final int MIN_NUMBER = 1;
    public static final int MAX_NUMBER = 45;
    public static final int SIZE = 6;

    public static final String INVALID_LOTTO_NUMBER_RANGE = "숫자는 1부터 45 사이여야 합니다.";
    public static final String INVALID_LOTTO_NUMBER_SIZE = "당첨번호는 6개여야 합니다.";
    public static final String INVALID_LOTTO_NUMBER_DUPLICATE = "중복된 숫자가 없어야 합니다.";

    private final List<Integer> numbers;

    public Lotto(final List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        validateDuplicate(numbers);
        validateRange(numbers);
        validateSize(numbers);
    }

    private void validateDuplicate(final List<Integer> numbers) {
        if (hasDuplicateNumber(numbers)) {
            throw new AppException(INVALID_LOTTO_NUMBER_DUPLICATE);
        }
    }

    private boolean hasDuplicateNumber(final List<Integer> numbers) {
        return numbers.stream().distinct().count() != numbers.size();
    }

    private void validateRange(List<Integer> numbers) {
        if (isOutOfRange(numbers)) {
            throw new AppException(INVALID_LOTTO_NUMBER_RANGE);
        }
    }

    private boolean isOutOfRange(final List<Integer> numbers) {
        return numbers.stream()
                .anyMatch(number -> number < MIN_NUMBER || number > MAX_NUMBER);
    }


    private void validateSize(List<Integer> numbers) {
        if(numbers.size() != SIZE) {
            throw new AppException(INVALID_LOTTO_NUMBER_SIZE);
        }
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

}
