package model.draw;

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

    private void validate(List<Integer> numbers) {
        LottoValidator.validateLottoNumberCount(numbers);
        LottoValidator.validateLottoNumberDuplication(numbers);
        for (int number : numbers) {
            LottoValidator.validateLottoNumberRange(number);
        }
    }
}
