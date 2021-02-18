package lotto.domain;

import java.util.List;

public class FixedNumberGenerator implements LottoNumberGenerator {

    private final List<Integer> numbers;

    public FixedNumberGenerator(List<Integer> numbers) {
        this.numbers = numbers;
    }

    @Override
    public List<Integer> generateNumbers() {
        return numbers;
    }
}
