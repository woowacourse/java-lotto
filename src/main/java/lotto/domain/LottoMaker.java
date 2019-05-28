package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class LottoMaker {
    private static final int INIT = 0;
    private static final int LOTTO_SIZE = 6;

    private static final List<Integer> numbers = new ArrayList<>();

    static {
        for (int i = 1; i <= 45; i++) {
            numbers.add(i);
        }
    }

    public static List<Integer> generator() {
        List<Integer> lottoNumbers = new ArrayList<>();

        mixNumbers();
        for (int i = INIT; i < LOTTO_SIZE; i++) {
            lottoNumbers.add(numbers.get(i));
        }
        return lottoNumbers;
    }

    private static void mixNumbers() {
        Collections.shuffle(numbers, new Random());
    }
}
