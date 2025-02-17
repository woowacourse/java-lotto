package model.purchase;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import model.common.LottoValidator;
import model.draw.BonusNumber;
import model.draw.WinningNumber;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
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

    private void validate(List<Integer> numbers) {
        LottoValidator.validateLottoNumberCount(numbers);
        LottoValidator.validateLottoNumberDuplication(numbers);
        for (int number : numbers) {
            LottoValidator.validateLottoNumberRange(number);
        }
    }
}
