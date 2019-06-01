package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lotto {
    public static final int LOTTO_SIZE = 6;

    private final List<Number> lotto;

    public Lotto(final List<Number> lotto) {
        validate(lotto);
        Collections.sort(lotto);
        this.lotto = lotto;
    }

    private void validate(final List<Number> lotto) {
        if (lotto.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException("로또는 6자리 입니다.");
        }
    }

    public List<Number> getLotto() {
        return new ArrayList<>(lotto);
    }

    @Override
    public String toString() {
        return lotto.toString();
    }

    public boolean isContain(Number number) {
        return lotto.contains(number);
    }
}
