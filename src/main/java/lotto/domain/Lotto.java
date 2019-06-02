package lotto.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lotto {
    private static final String ERROR_DUPLICATE_MESSAGE = "중복된 수가 있습니다.";
    private static final String ERROR_LOTTO_SIZE = "가질수 있는 로또의 수는 6개 입니다.";
    private static final int LOTTO_SIZE = 6;

    private final List<Number> lotto;

    public Lotto(List<Number> lotto) {
        this.lotto = lotto;
        validCheck();
    }

    private void validCheck() {
        validDulicate(lotto);
        validSize(lotto);
    }

    private void validDulicate(List<Number> lotto) {
        Set<Number> cheked = new HashSet<>(lotto);

        if (cheked.size() != lotto.size()) {
            throw new IllegalArgumentException(ERROR_DUPLICATE_MESSAGE);
        }
    }

    private void validSize(List<Number> lotto) {
        if (lotto.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException(ERROR_LOTTO_SIZE);
        }
    }

    @Override
    public String toString() {
        return lotto.toString();
    }
}
