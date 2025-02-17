package lotto.domain;

import java.util.List;
import lotto.util.NumberGenerator;

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

