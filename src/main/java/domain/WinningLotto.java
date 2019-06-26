package domain;

import domain.lottonumber.LottoNumber;

import java.util.Objects;
import java.util.Set;

public class WinningLotto extends Lotto {
    private LottoNumber bonusNumber;

    WinningLotto(Set<LottoNumber> lottoNumbers, LottoNumber bonusNumber) {
        super(lottoNumbers);
        this.bonusNumber = bonusNumber;
    }

    Rank matchUpLottoNumbersWith(IssuedLotto issuedLotto) {
        int countOfMatchingNumbers = (int) lottoNumbers.stream()
                .filter(issuedLotto::contains)
                .count();

        return Rank.of(countOfMatchingNumbers, issuedLotto.contains(bonusNumber));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WinningLotto that = (WinningLotto) o;
        return Objects.equals(bonusNumber, that.bonusNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bonusNumber);
    }
}
