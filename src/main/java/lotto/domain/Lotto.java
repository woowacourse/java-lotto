package lotto.domain;

import lotto.validator.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Lotto {
    private final List<LottoNo> lottoNumbers;

    public Lotto(List<LottoNo> lottoNumbers) {
        Validator.validateLottoSize(lottoNumbers);
        this.lottoNumbers = new ArrayList<>(lottoNumbers);
    }

    public boolean isContain(LottoNo lottoNo) {
        return lottoNumbers.contains(lottoNo);
    }

    public int compare(Lotto winLotto) {
        return (int) lottoNumbers.stream()
                .filter(x -> winLotto.isContain(x))
                .count();
    }

    @Override
    public String toString() {
        return lottoNumbers.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Lotto lotto = (Lotto) o;
        return Objects.equals(lottoNumbers, lotto.lottoNumbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoNumbers);
    }
}
