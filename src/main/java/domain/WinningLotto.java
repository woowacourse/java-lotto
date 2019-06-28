package domain;

import domain.lottonumber.LottoNumber;

import java.util.Objects;

public class WinningLotto {
    private Lotto lotto;
    private LottoNumber bonusNumber;

    WinningLotto(Lotto lotto, LottoNumber bonusNumber) {
        this.lotto = lotto;
        this.bonusNumber = bonusNumber;
    }

    Rank matchUpLottoNumbersWith(Lotto issuedLotto) {
        int countOfMatchingNumbers = (int) issuedLotto.lottoNumbers.stream()
                .filter(lotto::contains)
                .count();

        return Rank.of(countOfMatchingNumbers, issuedLotto.contains(bonusNumber));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WinningLotto that = (WinningLotto) o;
        return Objects.equals(lotto, that.lotto) &&
                Objects.equals(bonusNumber, that.bonusNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lotto, bonusNumber);
    }

    public LottoNumber getBonusNumber() {
        return bonusNumber;
    }

    @Override
    public String toString() {
        return lotto.toString();
    }
}
