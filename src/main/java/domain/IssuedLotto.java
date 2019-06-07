package domain;

import java.util.Objects;
import java.util.Set;

public class IssuedLotto extends Lotto{
    static final int PRICE = 1000;

    IssuedLotto(Set<LottoNumber> lottoNumbers) {
        super(lottoNumbers);
    }

    boolean contains(LottoNumber lottoNumber) {
        return lottoNumbers.contains(lottoNumber);
    }

    @Override
    public String toString() {
        return lottoNumbers.toString();
    }
}
