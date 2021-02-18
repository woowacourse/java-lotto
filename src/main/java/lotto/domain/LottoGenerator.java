package lotto.domain;

import java.util.*;

public class LottoGenerator {

    public static final int LOTTO_START_NUMBER = 1;
    public static final int LOTTO_END_NUMBER = 45;
    public static final int LOTTO_POSSESSION_NUMBER = 6;
    private static final List<Integer> candidateNumbers = new ArrayList<>();

    static {
        for (int number = LOTTO_START_NUMBER; number <= LOTTO_END_NUMBER; number++) {
            candidateNumbers.add(number);
        }
    }

    public static List<Integer> generateNumbers() {
        Collections.shuffle(candidateNumbers);
        Set<Integer> lottoNumbers =
                new TreeSet<>(candidateNumbers.subList(0, LOTTO_POSSESSION_NUMBER));
        return new ArrayList<>(lottoNumbers);
    }
}
