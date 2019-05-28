package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class LottoMaker {
    private static final int START = 0;
    private static final int LOTTO_SIZE = 6;
    private static final int FIRST_NUMBER = 1;
    private static final int LAST_NUMBER = 45;

    private static final List<Integer> numbers = new ArrayList<>();

    static {
        for (int i = FIRST_NUMBER; i <= LAST_NUMBER; i++) {
            numbers.add(i);
        }
    }

    public static List<Integer> generator() {
        mixNumbers();
        return numbers.subList(START, LOTTO_SIZE - 1);
    }

    private static void mixNumbers() {
        Collections.shuffle(numbers, new Random());
    }
}
