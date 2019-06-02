package lotto.domain.lotto;

import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

import lotto.exceptions.NumberCountException;

public class Lotto implements Iterable<Number> {
    private static final int LOTTO_SIZE = 6;
    private static final CharSequence JOINING_DELIMITER = ", ";

    private final List<Number> lotto;

    public Lotto(List<Number> lotto) {
        validFormat(lotto);
        this.lotto = lotto;
    }

    private void validFormat(List<Number> lotto) {
        TreeSet<Number> lottoSet = new TreeSet<>(lotto);
        if (lottoSet.size() != LOTTO_SIZE) {
            throw new NumberCountException();
        }
    }

    public boolean contains(Number number) {
        return lotto.contains(number);
    }

    @Override
    public String toString() {
        return lotto.stream()
                .map(Number::toString)
                .collect(Collectors.joining(JOINING_DELIMITER));
    }

    @Override
    public Iterator<Number> iterator() {
        return lotto.iterator();
    }
}
