package domain;

import domain.lottonumber.LottoNumber;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class WinningLotto extends Lotto {
    private LottoNumber bonusNumber;

    WinningLotto(Set<LottoNumber> lottoNumbers, LottoNumber bonusNumber) {
        super(lottoNumbers);
        this.bonusNumber = bonusNumber;
    }

    Rank matchUpLottoNumbersOf(IssuedLotto issuedLotto) {
        int countOfMatchingNumbers = lottoNumbers.stream()
                .filter(issuedLotto::contains)
                .collect(Collectors.toList())
                .size();

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
