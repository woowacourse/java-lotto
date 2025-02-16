package model;

import static constant.LottoConstant.LOTTO_NUMBER_COUNT;

import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class Lotto {
    private final Set<LottoNumber> lottoLottoNumbers;

    public Lotto(Set<LottoNumber> lottoLottoNumbers) {
        if (lottoLottoNumbers.size() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException("로또 번호는 6개여야 합니다.");
        }
        this.lottoLottoNumbers = new TreeSet<>(lottoLottoNumbers);
    }

    public boolean containsNumber(LottoNumber lottoNumber) {
        return lottoLottoNumbers.contains(lottoNumber);
    }

    public Set<LottoNumber> getLottoNumbers() {
        return new TreeSet<>(lottoLottoNumbers);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Lotto lotto)) {
            return false;
        }
        return Objects.equals(getLottoNumbers(), lotto.getLottoNumbers());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getLottoNumbers());
    }
}
