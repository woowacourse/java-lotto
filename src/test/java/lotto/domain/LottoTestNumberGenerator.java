package lotto.domain;

import lotto.util.NumberGenerator;

import java.util.List;

public class LottoTestNumberGenerator implements NumberGenerator {
    private final List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
    private int index = 0;

    @Override
    public int generate(int minimum, int maximum) {
        return numbers.get(index++);
    }
}
