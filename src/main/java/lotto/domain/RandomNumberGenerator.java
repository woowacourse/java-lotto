package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class RandomNumberGenerator {
    private List<Integer> numberPool = new ArrayList<>();

    private void makeNumberPool() {
        for (int i = 1; i <= 45; i++) {
            numberPool.add(i);
        }
    }

    private List<Integer> getInstance() {
        if (numberPool.isEmpty()) {
            makeNumberPool();
        }
        Collections.shuffle(numberPool);
        return numberPool;
    }

    public List<Integer> generateNumbers() {
        List<Integer> randomNumbers = this.getInstance();
        randomNumbers = randomNumbers.subList(0,6);
        Collections.sort(randomNumbers);
        return randomNumbers;
    }
}
