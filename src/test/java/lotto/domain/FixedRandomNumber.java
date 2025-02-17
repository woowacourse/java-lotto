package lotto.domain;

import java.util.Arrays;
import java.util.List;
import lotto.util.NumberGenerator;
import lotto.util.RandomNumber;

public class FixedRandomNumber implements NumberGenerator {
    private final List<Integer> numbers;
    private int index = 0;

    public FixedRandomNumber(List<Integer> numbers) {
        this.numbers = numbers;
    }

    @Override
    public int generate(int start, int end) {
        return numbers.get(index++ % numbers.size());
    }
}

