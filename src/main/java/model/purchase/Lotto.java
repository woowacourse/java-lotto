package model.purchase;

import exception.ExceptionMessage;
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
            throw new IllegalStateException(ExceptionMessage.OUT_OF_LOTTO_SIZE);
        }
    }

    private void validateNumberDuplication(List<Integer> numbers) {
        if (new HashSet<>(numbers).size() != numbers.size()) {
            throw new IllegalArgumentException(ExceptionMessage.DUPLICATED_LOTTO_NUMBER);
        }
    }

    private void validateNumberRange(int number) {
        if (number < LottoConstants.MIN_NUMBER || number > LottoConstants.MAX_NUMBER) {
            throw new IllegalStateException(ExceptionMessage.OUT_OF_LOTTO_NUMBER_RANGE);
        }
    }
}
