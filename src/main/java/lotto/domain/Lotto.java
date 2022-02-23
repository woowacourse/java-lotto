package lotto.domain;

import java.util.List;
import lotto.utils.Validation;

public class Lotto {

    final List<Integer> numbers;

    public Lotto(final List<Integer> numbers) {
        Validation.checkDuplicateNumber(numbers);
        this.numbers = numbers;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

}
