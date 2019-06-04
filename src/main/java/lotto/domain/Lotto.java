package lotto.domain;

import java.util.List;
import java.util.Objects;

public class Lotto {
    private final LottoNumbers lottoNumbers;

    Lotto(final LottoNumbers lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
    }

    boolean hasLottoNumber(final LottoNumber lottoNumber) {
        return lottoNumbers.hasLottoNumber(lottoNumber);
    }

    int hasLottoNumber(final LottoNumbers lottoNumbers) {
        return this.lottoNumbers.hasLottoNumber(lottoNumbers);
    }

    public List<Integer> getLottoNumbers() {
        return lottoNumbers.getLottoNumbers();
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
}
