package lotto.domain;

import java.util.*;

public class RandomNumberGenerator {

    private static List<Integer> allLottoNumbers = new ArrayList<>();

    static {
        for (int i = 1; i <= 45; i++) {
            allLottoNumbers.add(i);
        }
    }

    public List<Integer> generate() {
        Collections.shuffle(allLottoNumbers);
        return allLottoNumbers.subList(0, 6);
    }
}
