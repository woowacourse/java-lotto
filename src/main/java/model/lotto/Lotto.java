package model.lotto;

import java.util.List;
import validator.Validator;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        for (Integer number : numbers) {
            Validator.validateLottoNumberRange(number);
        }

        this.numbers = numbers;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }
}