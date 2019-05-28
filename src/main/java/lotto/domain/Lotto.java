package lotto.domain;

import java.util.List;

public class Lotto {
    private final List<LottoNumber> lotto;

    public Lotto(List<LottoNumber> lotto) {
        this.lotto = lotto;
    }

    public int size() {
        return lotto.size();
    }
}
