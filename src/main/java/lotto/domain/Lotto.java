package lotto.domain;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
//
//public class Lotto implements Iterable<LottoNumber> {
//    private static final CharSequence JOINING_DELIMITER = ", ";
//
//    private final List<LottoNumber> lotto;
//
//    public Lotto(List<LottoNumber> lotto) {
//        this.lotto = lotto;
//    }
//
//    public String getLotto() {
//        return lotto.stream()
//                .map(Number::getBonusNumber)
//                .collect(Collectors.joining(JOINING_DELIMITER));
//    }
//
//    public boolean contains(Number number) {
//        return lotto.contains(number);
//    }
//
//    @Override
//    public Iterator<LottoNumber> iterator() {
//        return lotto.iterator();
//    }
//}


public class Lotto implements Iterable<Number> {
    private static final CharSequence JOINING_DELIMITER = ", ";

    private final List<Number> lotto;

    public Lotto(List<Number> lotto) {
        this.lotto = lotto;
    }

    public String getLotto() {
        return lotto.stream()
                .map(Number::getBonusNumber)
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
