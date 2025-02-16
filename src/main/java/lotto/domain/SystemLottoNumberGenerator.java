package lotto.domain;

import static lotto.domain.Lotto.LOTTO_SIZE;
import static lotto.domain.Lotto.MAX_LOTTO_NUMBER;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import lotto.LottoNumberGenerator;

public class SystemLottoNumberGenerator implements LottoNumberGenerator {
    @Override
    public List<Integer> generate() {
        Set<Integer> numbers = new HashSet<>();
        while (numbers.size() < LOTTO_SIZE) {
            numbers.add(new Random().nextInt(MAX_LOTTO_NUMBER - 1) + 1);
        }
        return new ArrayList<>(numbers);
    }
}
