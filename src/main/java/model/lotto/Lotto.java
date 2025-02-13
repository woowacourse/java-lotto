package model.lotto;

import java.util.List;
import validator.Validator;

public class Lotto {
    private List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        for (int idx = 0; idx < numbers.size(); idx++) {
            Validator.validateLottoNumberRange(numbers.get(idx));
        }

        this.numbers = numbers;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }
}