package model;

import static constant.LottoConstant.LOTTO_NUMBER_COUNT;

import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class Lotto {
    private final Set<Number> lottoNumbers;

    public Lotto(Set<Number> lottoNumbers) {
        if (lottoNumbers.size() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException("로또 번호는 6개여야 합니다.");
        }
        this.lottoNumbers = new TreeSet<>(lottoNumbers);
    }

    public boolean containsNumber(Number number) {
        return lottoNumbers.contains(number);
    }

    public Set<Number> getLottoNumbers() {
        return new TreeSet<>(lottoNumbers);
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
