package lotto.domain.generator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import lotto.domain.Lotto;
import lotto.domain.Number;

public class LottoAutoNumberGenerator implements LottoNumberGenerator {

    private static final List<Integer> candidateNumbers = new ArrayList<>();

    static {
        for (int number = Number.LOWER_LIMIT; number <= Number.UPPER_LIMIT; number++) {
            candidateNumbers.add(number);
        }
    }

    @Override
    public synchronized List<Integer> generateNumbers() {
        final List<Integer> shuffledNumbers = Collections.synchronizedList(candidateNumbers);
        Collections.shuffle(shuffledNumbers);
        Set<Integer> lottoNumbers =
            new TreeSet<>(shuffledNumbers.subList(0, Lotto.LOTTO_POSSESSION_NUMBER));
        return new ArrayList<>(lottoNumbers);
    }
}
