package model.draw;

import exception.ExceptionMessage;
import java.util.List;
import model.common.LottoValidator;

public class WinningNumber {
    private final List<Integer> numbers;

    public WinningNumber(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    public boolean contains(int targetNumber) {
        return numbers.contains(targetNumber);
    }

    public void validateDuplicationWith(int bonusNumber) {
        if (numbers.contains(bonusNumber)) {
            throw new IllegalArgumentException(ExceptionMessage.DUPLICATED_BONUS_NUMBER);
        }
    }

    private void validate(List<Integer> numbers) {
        LottoValidator.validateLottoNumberCount(numbers);
        LottoValidator.validateLottoNumberDuplication(numbers);
        for (int number : numbers) {
            LottoValidator.validateLottoNumberRange(number);
        }
    }
}
