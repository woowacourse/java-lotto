package lotto;

import java.util.List;

public class Lotto {

    private static final int LOTTO_SIZE = 6;
    private static final int MINIMUM_NUMBER = 1;
    private static final int MAXIMUM_NUMBER = 45;

    public Lotto(List<Integer> numbers) {
        validateSize(numbers);
        validateRangeOfNumbers(numbers);
    }

    private void validateRangeOfNumbers(List<Integer> numbers) {
        for (int number : numbers) {
            validateRangeOfNumber(number);
        }
    }

    private void validateRangeOfNumber(int number) {
        if (number < MINIMUM_NUMBER || number > MAXIMUM_NUMBER) {
            throw new IllegalArgumentException("로또 번호는 1이상 45이하이어야 한다.");
        }
    }

    private void validateSize(List<Integer> numbers) {
        if (numbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException("로또 번호는 6자리 이어야 한다.");
        }
    }
}
