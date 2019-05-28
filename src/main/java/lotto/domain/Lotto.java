package lotto.domain;

import java.util.List;

public class Lotto {
    List<Number> lotto;

    public Lotto(List<Number> lotto) {
        validate(lotto);
        this.lotto = lotto;
    }

    private void validate(List<Number> lotto) {
        if (lotto.size() != 6) {
            throw new IllegalArgumentException("로또는 6자리 입니다.");
        }
    }

}
