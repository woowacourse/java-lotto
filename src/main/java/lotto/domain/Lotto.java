package lotto.domain;

import lotto.vaildator.LogicValidator;

import java.util.Collections;
import java.util.List;

public class Lotto {
    public final int MIN_LOTTO_NUMBER = 1;
    public final int MAX_LOTTO_NUMBER = 45;
    public final int LOTTO_NUMBER_COUNT = 6;
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        LogicValidator.validateDuplication(numbers);
        LogicValidator.validateRange(numbers, MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER);
        LogicValidator.validateSize(numbers, LOTTO_NUMBER_COUNT);
        this.numbers = numbers.stream().sorted().toList();
    }

    public List<Integer> getNumbers() {
        return Collections.unmodifiableList(this.numbers);
    }
}
