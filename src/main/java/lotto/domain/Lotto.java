package lotto.domain;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

public class Lotto {
    private List<Integer> lottoNumbers;

    private Lotto(List<Integer> lottoNumbers) {
        sortAscending(lottoNumbers);
        this.lottoNumbers = lottoNumbers;
    }

    private void sortAscending(List<Integer> lottoNumbers) {
        Collections.sort(lottoNumbers);
    }

    public static Lotto of(List<Integer> lottoNumbers) {
        return new Lotto(lottoNumbers);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lotto lotto = (Lotto) o;
        return Objects.equals(lottoNumbers, lotto.lottoNumbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoNumbers);
    }

    @Override
    public String toString() {
        StringJoiner stringJoiner = new StringJoiner(", ", "[", "]");
        for (Integer lottoNumber : lottoNumbers) {
            stringJoiner.add(String.valueOf(lottoNumber));
        }
        return stringJoiner.toString();
    }
}
