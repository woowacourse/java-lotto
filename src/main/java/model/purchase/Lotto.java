package model.purchase;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import model.LottoConstants;
import model.draw.BonusNumber;
import model.draw.WinningNumber;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validateNumberCount(numbers);
        validateNumberDuplication(numbers);
        for (int number : numbers) {
            validateNumberRange(number);
        }
        this.numbers = numbers;
    }

    public boolean isDuplicatedWith(List<Integer> targetNumbers) {
        return new HashSet<>(numbers).containsAll(targetNumbers);
    }

    public int findMatchingCountWith(WinningNumber winningNumber) {
        return (int) numbers.stream()
                .filter(number -> winningNumber.contains(number))
                .count();
    }

    public boolean contains(BonusNumber bonusNumber) {
        return this.numbers.contains(bonusNumber.getNumber());
    }

    public List<Integer> getNumbers() {
        return Collections.unmodifiableList(numbers);
    }

    private void validateNumberCount(List<Integer> numbers) {
        if (numbers.size() != LottoConstants.NUMBER_COUNT) {
            throw new IllegalStateException("로또 번호의 개수는 6입니다.");
        }
    }

    private void validateNumberDuplication(List<Integer> numbers) {
        if (new HashSet<>(numbers).size() != numbers.size()) {
            throw new IllegalArgumentException("로또 번호는 중복이 허용되지 않습니다.");
        }
    }

    private void validateNumberRange(int number) {
        if (number < LottoConstants.MIN_NUMBER || number > LottoConstants.MAX_NUMBER) {
            throw new IllegalStateException("로또 번호는 1~45 사이의 정수입니다.");
        }
    }
}
