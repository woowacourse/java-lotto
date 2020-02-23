package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class RandomNumberGenerator {
    private static final int MAX_LOTTO_NUMBER = 45;
    private static final int MIN_LOTTO_NUMBER = 1;
    private List<Integer> numberPool = new ArrayList<>();

    private void makeNumberPool() {
        for (int i = MIN_LOTTO_NUMBER; i <= MAX_LOTTO_NUMBER; i++) {
            numberPool.add(i);
        }
    }

    public List<Integer> generateNumbers() {
        if (numberPool.isEmpty()) {
            makeNumberPool();
        }
        Collections.shuffle(numberPool);
        List<Integer> randomNumbers = numberPool.subList(0, Lotto.LOTTO_NUMBER_AMOUNT);
        Collections.sort(randomNumbers);
        return randomNumbers;
    }
}
