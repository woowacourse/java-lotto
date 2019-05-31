package lotto.domain;

import java.util.List;

public class Lotto {
    private final List<Number> lotto;

    private Lotto(List<Number> lotto) {
        this.lotto = lotto;
    }

    public static Lotto create(List<Number> lotto) {
        return new Lotto(lotto);
    }

    public List<Number> getLotto() {
        return lotto;
    }

    public Number IndexByNumber(int index) {
        return lotto.get(index);
    }

    public boolean isContains(Number lottoNumber) {
        return lotto.contains(lottoNumber);
    }

    public int getSize() {
        return lotto.size();
    }
}
