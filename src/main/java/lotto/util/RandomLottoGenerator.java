package lotto.util;

import lotto.model.Lotto;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomLottoGenerator {

    private static final List<Integer> NUMBER_RANGE = IntStream.range(1,46).boxed().collect(Collectors.toList());

    public static Lotto generate() {
        Collections.shuffle(NUMBER_RANGE);
        return new Lotto(NUMBER_RANGE.subList(0,6));
    }
}
