package lotto.domain.utils;

import lotto.domain.model.Number;
import lotto.domain.model.NumberSet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShuffledNumberGenerator {
    private static final List<Number> numberPoll = new ArrayList<>();

    static {
        for (int key : NumberSet.lottoNumbers.keySet()) {
            numberPoll.add(NumberSet.of(key));
        }
    }

    private static final int LOTTO_SIZE = 6;

    public static List<Number> getShuffledNumbers() {
        Collections.shuffle(numberPoll);
        return numberPoll.subList(0, LOTTO_SIZE);
    }
}
