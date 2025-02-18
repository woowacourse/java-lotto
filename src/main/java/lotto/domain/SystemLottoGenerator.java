package lotto.domain;

import static lotto.domain.Lotto.LOTTO_SIZE;
import static lotto.domain.Lotto.MAX_LOTTO_NUMBER;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class SystemLottoGenerator implements LottoGenerator {
    @Override
    public Lotto generate() {
        Set<Integer> numbers = new HashSet<>();
        while (numbers.size() < LOTTO_SIZE) {
            numbers.add(new Random().nextInt(MAX_LOTTO_NUMBER - 1) + 1);
        }
        return new Lotto(new ArrayList<>(numbers));
    }
}
