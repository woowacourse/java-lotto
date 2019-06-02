package lotto.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lotto {
    private static final String ERROR_MESSAGE = "중복이 있거나 6개가 입력되지 않았습니다.";
    private static final int LOTTO_SIZE = 6;
    private final List<Number> lotto;

    public Lotto(List<Number> lotto) {
        if (validDulicate(lotto) || validSize(lotto)) {
            throw new IllegalArgumentException(ERROR_MESSAGE);
        }
        this.lotto = lotto;
    }

    private boolean validDulicate(List<Number> lotto) {
        Set<Number> cheked = new HashSet<>(lotto);
        return cheked.size() != lotto.size();
    }

    private boolean validSize(List<Number> lotto) {
        return lotto.size() != LOTTO_SIZE;
    }
}
