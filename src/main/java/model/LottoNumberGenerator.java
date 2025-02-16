package model;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class LottoNumberGenerator implements RandomNumberGenerator {
    private static final int LOTTO_SIZE = 6;
    private static final int LOTTO_NAX_SIZE = 45;
    private static final int LOTTO_MIN_SIZE = 1;

    private final Random random = new Random();

    @Override
    public Set<Integer> generateNumbers() {
        Set<Integer> lotto = new HashSet<>();

        while (lotto.size() < LOTTO_SIZE) {
            int number = random.nextInt(LOTTO_NAX_SIZE) + LOTTO_MIN_SIZE;
            lotto.add(number);
        }
        return lotto;
    }
}
