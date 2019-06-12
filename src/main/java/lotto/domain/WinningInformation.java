package lotto.domain;

import lotto.exception.DuplicateBonusBallException;

import java.util.List;
import java.util.Objects;

public class WinningInformation {
    private final LottoNumbers winningLottoNumbers;
    private final LottoNumber bonusBall;

    public WinningInformation(final LottoNumbers lottoNumbers, final LottoNumber bonusBall) {
        if (lottoNumbers.hasLottoNumber(bonusBall)) {
            throw new DuplicateBonusBallException();
        }
        this.winningLottoNumbers = lottoNumbers;
        this.bonusBall = bonusBall;
    }

    Rank match(final Lotto lotto) {
        return Rank.valueOf(lotto.hasLottoNumber(winningLottoNumbers), lotto.hasLottoNumber(bonusBall));
    }

    public List<Integer> getWinningLottoNumbers() {
        return winningLottoNumbers.getLottoNumbers();
    }

    public int getBonusNumber() {
        return bonusBall.getNumber();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WinningInformation that = (WinningInformation) o;
        return Objects.equals(winningLottoNumbers, that.winningLottoNumbers) &&
                Objects.equals(bonusBall, that.bonusBall);
    }

    @Override
    public int hashCode() {
        return Objects.hash(winningLottoNumbers, bonusBall);
    }
}
