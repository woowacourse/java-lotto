package lotto.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WinningNumber {

    private final List<Integer> winningNumbers;

    public WinningNumber(List<Integer> numbers) {
        validateSize(numbers);
        validateDuplicate(numbers);
        this.winningNumbers = numbers;
    }

    private void validateSize(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("당첨 번호는 6개여야 합니다.");
        }
    }

    private void validateDuplicate(List<Integer> numbers) {
        Set<Integer> set = new HashSet<>(numbers);
        if (set.size() != 6) {
            throw new IllegalArgumentException("당첨 번호는 중복될 수 없습니다.");
        }
    }


}
