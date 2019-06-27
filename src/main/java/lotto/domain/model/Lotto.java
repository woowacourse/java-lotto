package lotto.domain.model;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Lotto {

    private List<Number> lotto;

    public Lotto(List<Number> lottoNumbers) {
        Collections.sort(lottoNumbers);
        this.lotto = lottoNumbers;
    }

    public boolean isContained(Number lottoNumber) {
        return lotto.contains(lottoNumber);
    }

    public List<Number> getLottoNumbers() {
        return Collections.unmodifiableList(lotto);
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

    @Override
    public String toString() {
        String lottoString = "[";

        for (int i = 0; i < 5; i++) {
            lottoString += (lotto.get(i).getNumber() + ", ");
        }

        lottoString += (lotto.get(5).getNumber() + "]");
        return lottoString;
    }
}
