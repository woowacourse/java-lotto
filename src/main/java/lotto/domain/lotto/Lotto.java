package lotto.domain.lotto;

import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

import lotto.domain.exceptions.IllegalFormatException;

public class Lotto implements Iterable<Number> {
    private static final CharSequence JOINING_DELIMITER = ", ";

    private final List<Number> lotto;

    public Lotto(List<Number> lotto) {
        validFormat(lotto);
        this.lotto = lotto;
    }

    private void validFormat(List<Number> lotto) {
        TreeSet<Number> lottoSet = new TreeSet<>(lotto);
        if (lottoSet.size() != 6) {
            throw new IllegalFormatException("로또 번호는 6개 입니다.");
        }
    }

    public String getLotto() {
        return lotto.stream()
                .map(Number::getNumber)
                .collect(Collectors.joining(JOINING_DELIMITER));
    }

    public boolean contains(Number number) {
        return lotto.contains(number);
    }

    @Override
    public Iterator<Number> iterator() {
        return lotto.iterator();
    }
}
