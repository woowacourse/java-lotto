package lotto.domain;

import java.util.List;
import java.util.Objects;

public class Lotto {

    private List<Number> lotto;

    public Lotto(List<Number> lottoNumbers) {
        this.lotto = lottoNumbers;
    }

    public boolean isContained(Number lottoNumber) {
        return lotto.contains(lottoNumber);
    }

    public List<Number> getLotto() {
        return lotto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lotto lotto = (Lotto) o;
        return Objects.equals(this.lotto, lotto.lotto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lotto);
    }
}
