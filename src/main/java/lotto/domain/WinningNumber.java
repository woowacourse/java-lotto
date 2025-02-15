package lotto.domain;

import lotto.domain.constant.LottoConstants;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WinningNumber {

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
        if (numbers.size() != LottoConstants.NUMBER_COUNT) {
            throw new IllegalArgumentException("당첨 번호는 " + LottoConstants.NUMBER_COUNT + "개여야 합니다.");
        }
    }

    private void validateDuplicate(List<Integer> numbers) {
        Set<Integer> set = new HashSet<>(numbers);
        if (set.size() != LottoConstants.NUMBER_COUNT) {
            throw new IllegalArgumentException("당첨 번호는 중복될 수 없습니다.");
        }
    }

    private void validateRange(List<Integer> numbers) {
        if (numbers.stream()
                .anyMatch(number -> number < LottoConstants.MIN_NUMBER || number > LottoConstants.MAX_NUMBER)) {
            throw new IllegalArgumentException("당첨 번호는 " + LottoConstants.MIN_NUMBER
                    + "~" + LottoConstants.MAX_NUMBER + "사이의 수여야 합니다.");
        }
    }


}
