package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class LottoGenerator {
    private static List<Integer> candidateNumbers = new ArrayList<>();

    static {
        for (int number = 1; number <= 45; number++) {
            candidateNumbers.add(number);
        }
    }

    public static List<Integer> generateNumbers () {
        Collections.shuffle(candidateNumbers);
        Set<Integer> lottoNumbers = new TreeSet<>();
        for (int i = 0; i < 6; i++) {
            lottoNumbers.add(candidateNumbers.get(i));
        }
        return new ArrayList<>(lottoNumbers);
    }
}
