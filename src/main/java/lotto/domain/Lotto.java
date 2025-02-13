package lotto.domain;

import java.util.Collections;
import java.util.List;
import lotto.vaildator.LogicValidator;

public class Lotto {

    public static final int MIN_LOTTO_NUMBER = 1;
    public static final int MAX_LOTTO_NUMBER = 45;
    public static final int LOTTO_NUMBER_COUNT = 6;
    public static final int LOTTO_PRICE = 1000;
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

    public boolean hasNumber(int target) {
        return this.numbers.stream().anyMatch(number -> number == target);
    }

    public int findMatches(Lotto lotto) {
        return (int) numbers.stream().filter(lotto::hasNumber).count();
    }
}
