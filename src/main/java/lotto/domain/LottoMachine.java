package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lotto.domain.vo.Number;

public class LottoMachine {

    private static final int START_INCLUSIVE = 0;
    private static final int END_EXCLUSIVE = 6;
    private static final int MINIMUM_NUMBER = 1;
    private static final int MAXIMUM_NUMBER = 45;

    private static final List<Number> NUMBERS = initNumbers();

    public static Lotto generate() {
        return new Lotto(shuffleNumbers().subList(START_INCLUSIVE, END_EXCLUSIVE));
    }

    private static ArrayList<Number> shuffleNumbers() {
        ArrayList<Number> numbers = new ArrayList<>(LottoMachine.NUMBERS);
        Collections.shuffle(numbers);
        return numbers;
    }

    private static List<Number> initNumbers() {
        return IntStream.rangeClosed(MINIMUM_NUMBER, MAXIMUM_NUMBER)
            .mapToObj(Number::new)
            .collect(Collectors.toList());
    }
}
