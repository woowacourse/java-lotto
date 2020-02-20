package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class RandomNumberGenerator {
    public static List<Integer> generateNumbers() {
        List<Integer> lottoNumberPool = generateNumberPool();
        List<Integer> randomNumbers = new ArrayList<>();
        for (int i = 0; i < Lotto.LOTTO_NUMBER_AMOUNT; i++) {
            randomNumbers.add(lottoNumberPool.remove(lottoNumberPool.size() - 1));
        }
        Collections.sort(randomNumbers);
        return randomNumbers;
    }

    private static List<Integer> generateNumberPool() {
        List<Integer> lottoNumberPool = new LinkedList<>();
        for (int i = 1; i <= 45; i++) {
            lottoNumberPool.add(i);
        }
        Collections.shuffle(lottoNumberPool);
        return lottoNumberPool;
    }
}
