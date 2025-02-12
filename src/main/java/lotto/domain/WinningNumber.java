package lotto.domain;

import java.util.List;

public class WinningNumber {

    private final List<Integer> winningNumbers;

    public WinningNumber(List<Integer> numbers) {
        validateSize(numbers);
        this.winningNumbers = numbers;
    }

    private void validateSize(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("당첨 번호는 6개여야 합니다.");
        }
    }


}
