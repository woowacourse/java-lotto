package lotto.domain;

import java.util.List;

public class Lotto {
    private static final int LOTTO_SIZE = 6;

    List<Number> lotto;

    public Lotto(List<Number> lotto) {
        validate(lotto);
        this.lotto = lotto;
    }

    private void validate(List<Number> lotto) {
        if (lotto.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException("로또는 6자리 입니다.");
        }
    }

}
