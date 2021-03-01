package lotto.domain.generator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import lotto.domain.Lotto;
import lotto.domain.Number;

public class LottoAutoNumberGenerator implements LottoNumberGenerator {

    private final List<Number> candidateNumbers = new ArrayList<>();

    public LottoAutoNumberGenerator() {
        for (int number = Number.LOWER_LIMIT; number <= Number.UPPER_LIMIT; number++) {
            candidateNumbers.add(Number.from(number));
        }
    }

    @Override
    public synchronized List<Number> generateNumbers() {
        Collections.shuffle(candidateNumbers);
        Set<Number> lottoNumbers =
            new TreeSet<>(candidateNumbers.subList(0, Lotto.POSSESSION_NUMBER));
        return new ArrayList<>(lottoNumbers);
    }
}
