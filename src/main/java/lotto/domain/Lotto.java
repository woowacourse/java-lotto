package lotto.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Lotto {
    private static final int LOTTO_SIZE = 6;

    private final List<LottoNumber> lottoNumbers;

    Lotto(List<LottoNumber> lottoNumbers) {
        validateDuplication(lottoNumbers);
        this.lottoNumbers = lottoNumbers;
    }

    private void validateDuplication(List<LottoNumber> lottoNumbers) {
        Set<LottoNumber> nonDuplicatedLottoNumbers = new HashSet<>(lottoNumbers);
        if (nonDuplicatedLottoNumbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException("숫자가 중복 입력되었거나, 로또의 숫자가 6개가 아닙니다.");
        }
    }

    boolean contains(LottoNumber lottoNumber) {
        return lottoNumbers.contains(lottoNumber);
    }

    @Override
    public String toString() {
        return lottoNumbers.toString();
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
