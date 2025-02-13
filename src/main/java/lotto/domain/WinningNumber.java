package lotto.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WinningNumber {

    private static final int WINNING_NUMBER_COUNT = 6;
    private static final int LOTTO_START_NUMBER = 1;
    private static final int LOTTO_END_NUMBER = 45;

    private final List<Integer> winningNumbers;

    public WinningNumber(List<Integer> numbers) {
        validateSize(numbers);
        validateDuplicate(numbers);
        validateRange(numbers);
        this.winningNumbers = numbers;
    }

    public int countMatchingNumber(List<Integer> numbers) {
        return (int) numbers.stream()
                .filter(winningNumbers::contains)
                .count();
    }

    private void validateSize(List<Integer> numbers) {
        if (numbers.size() != WINNING_NUMBER_COUNT) {
            throw new IllegalArgumentException("당첨 번호는 " + WINNING_NUMBER_COUNT + "개여야 합니다.");
        }
    }

    private void validateDuplicate(List<Integer> numbers) {
        Set<Integer> set = new HashSet<>(numbers);
        if (set.size() != WINNING_NUMBER_COUNT) {
            throw new IllegalArgumentException("당첨 번호는 중복될 수 없습니다.");
        }
    }

    private void validateRange(List<Integer> numbers) {
        if (numbers.stream()
                .anyMatch(number -> number < LOTTO_START_NUMBER || number > LOTTO_END_NUMBER)) {
            throw new IllegalArgumentException("당첨 번호는 " + LOTTO_START_NUMBER
                    + "~" + LOTTO_END_NUMBER + "사이의 수여야 합니다.");
        }
    }


}
