package domain;

import exception.ExceptionMessage;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Lotto {
    public static final int LOTTO_SIZE = 6;

    private final Set<LottoNumber> lotto;

    public Lotto(Set<LottoNumber> lottoNumbers) {
        checkLottoSize(lottoNumbers);
        this.lotto = new HashSet<>(lottoNumbers);
    }

    private void checkLottoSize(Set<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_LOTTO_SIZE.getMessage());
        }
    }

    public int calculateMatchCount(Lotto targetLotto) {
        return (int) lotto.stream()
                .filter(targetLotto::isContain)
                .count();
    }

    public boolean isContain(LottoNumber lottoNumber) {
        return lotto.contains(lottoNumber);
    }

    public Set<LottoNumber> getLotto() {
        return Collections.unmodifiableSet(lotto);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Lotto lotto1 = (Lotto) o;
        return Objects.equals(lotto, lotto1.lotto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lotto);
    }
}
