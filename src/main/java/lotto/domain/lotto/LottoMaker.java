package lotto.domain.lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoMaker {
    private static final int START = 0;

    private static final List<Integer> numbers = new ArrayList<>();

    static {
        for (int i = Lotto.FIRST_NUMBER; i <= Lotto.LAST_NUMBER; i++) {
            numbers.add(i);
        }
    }

    public static Lotto generator(Numbers inputNumbers) {
        Collections.sort(inputNumbers.getNumbers());
        return Lotto.of(inputNumbers);
    }

    public static Lotto generator() {
        List<Integer> autoNumbers = new ArrayList<>(numbers);
        Collections.shuffle(autoNumbers);
        autoNumbers = autoNumbers.subList(START, Lotto.LOTTO_SIZE);
        Collections.sort(autoNumbers);
        return Lotto.of(new Numbers(autoNumbers));
    }
}
