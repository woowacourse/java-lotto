package lotto.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WinningNumber {

    private static final int WINNING_NUMBER_COUNT = 6;

    private final List<Integer> winningNumbers;

    public WinningNumber(List<Integer> numbers) {
        validateSize(numbers);
        validateDuplicate(numbers);
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
        for (Integer number : numbers) {
            if (number < 1 || number > 45) {
                throw new IllegalArgumentException("당첨 번호는 1~45 사이의 수여야 합니다.");
            }
        }
    }


}
