package domain;

import static domain.LottoRules.MAX_NUMBER;
import static domain.LottoRules.MIN_NUMBER;
import static error.ErrorMessage.BONUS_NUMBER_ALREADY_EXIST;
import static error.ErrorMessage.INVALID_NUMBER_RANGE;

import java.util.List;

public class WinningNumber {

    private final Lotto numbers;
    private final int bonusNumber;

    private WinningNumber(Lotto numbers, int bonusNumber) {
        validate(numbers, bonusNumber);
        this.numbers = numbers;
        this.bonusNumber = bonusNumber;
    }

    public static WinningNumber of(Lotto numbers, int bonusNumber) {
        return new WinningNumber(numbers, bonusNumber);
    }

    public boolean hasBonusNumber(Lotto lotto) {
        List<Integer> numbers = lotto.getNumbers();
        return numbers.contains(bonusNumber);
    }

    public int calculateMatchNumber(Lotto lotto) {
        List<Integer> lottoNumbers = lotto.getNumbers();
        List<Integer> numbers = this.numbers.getNumbers();
        return (int) lottoNumbers.stream()
            .filter(numbers::contains)
            .count();
    }

    public Lotto getNumbers() {
        return numbers;
    }

    public int getBonusNumber() {
        return bonusNumber;
    }

    private void validate(Lotto numbers, int bonusNumber) {
        validateNumbersRange(bonusNumber);
        validateBonusNumber(numbers, bonusNumber);
    }

    private void validateNumbersRange(int bonusNumber) {
        if (bonusNumber < MIN_NUMBER || bonusNumber > MAX_NUMBER) {
            throw new IllegalArgumentException(INVALID_NUMBER_RANGE.getMessage());
        }
    }

    private void validateBonusNumber(Lotto numbers, int bonusNumber) {
        if (numbers.hasBonusNumber(bonusNumber)) {
            throw new IllegalArgumentException(BONUS_NUMBER_ALREADY_EXIST.getMessage());
        }
    }
}
