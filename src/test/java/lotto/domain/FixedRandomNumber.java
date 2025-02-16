package lotto.domain;

import java.util.Arrays;
import java.util.List;

public class FixedRandomNumber extends RandomNumber {
    private final List<Integer> fixedNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
    private int index = 0;

    @Override
    public int generateRandomNumber(int start, int end) {
        return fixedNumbers.get(index++ % fixedNumbers.size());
    }
}
