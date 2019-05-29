package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class LottoGenerator {
    private static final int MINIMUM_LOTTO_NUMBER = 1;
    private static final int MAXIMUM_LOTTO_NUMBER = 45;

    private final List<Number> numberList;

    public LottoGenerator() {
        numberList = new ArrayList<>();
        IntStream.range(MINIMUM_LOTTO_NUMBER, MAXIMUM_LOTTO_NUMBER)
                .forEach(i -> numberList.add(new Number(i)));
    }

    public Lotto generate() {
        Collections.shuffle(numberList);
        List<Number> result = numberList.subList(0, 6);
        Collections.sort(result);
        return new Lotto(result);
    }

    public static Lotto generate(List<Number> numbers) {
        return new Lotto(numbers);
    }
}