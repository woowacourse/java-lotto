package domain.Lotto;

import utils.ExceptionMessage;

import java.util.List;

public class WinningLotto {

    private final Lotto winningLotto;
    private final LottoNumber bonusBall;

    public WinningLotto(Lotto winningLotto, LottoNumber bonusBallNumber) {
        validateDuplicate(winningLotto, bonusBallNumber);
        this.winningLotto = winningLotto;
        bonusBall = bonusBallNumber;
    }

    private void validateDuplicate(Lotto winningLotto, LottoNumber bonusBallNumber) {
        if (winningLotto.isContainLottoNumber(bonusBallNumber)) {
            throw new IllegalArgumentException(ExceptionMessage.LOTTO_AND_BONUS_BALL_DUPLICATION);
        }
    }

    public boolean isContainLottoNumber(LottoNumber lottoNumber) {
        return winningLotto.isContainLottoNumber(lottoNumber);
    }

    public boolean isContainBonusBall(List<LottoNumber> lottoNumbers) {
        return lottoNumbers.contains(bonusBall);
    }
}
