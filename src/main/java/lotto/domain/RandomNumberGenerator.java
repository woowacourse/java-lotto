package lotto.domain;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class RandomNumberGenerator {
    private final List<Integer> lottoNumberPool = new LinkedList<>();

    public RandomNumberGenerator() {
        for (int i = 1; i <= 45; i++) {
            lottoNumberPool.add(i);
        }
        Collections.shuffle(lottoNumberPool);
    }

    public int generate() {
        return lottoNumberPool.remove(0);
    }
}
