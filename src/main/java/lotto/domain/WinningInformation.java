package lotto.domain;

import lotto.exception.DuplicateBonusBallException;

import java.util.List;

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
}
