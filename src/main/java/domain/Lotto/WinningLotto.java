package domain.Lotto;

import utils.ExceptionMessage;

import java.util.List;

public class WinningLotto {

    private final Lotto winningLotto;
    private final LottoNumber bonusBall;

    public WinningLotto(Lotto winningLotto, LottoNumber bonusBallNumber) {
        validateDuplicate(winningLotto.getLotto(), bonusBallNumber);
        this.winningLotto = winningLotto;
        bonusBall = bonusBallNumber;
    }

    public boolean isContainLottoNumber(LottoNumber lottoNumber) {
        List<LottoNumber> winningLottoNumbers = winningLotto.getLotto();
        return winningLottoNumbers.contains(lottoNumber);
    }

    public boolean isContainBonusBall(List<LottoNumber> lottoNumbers) {
        return lottoNumbers.contains(bonusBall);
    }

    public void validateDuplicate(List<LottoNumber> numbers, LottoNumber bonusBallNumber) {
        if (numbers.contains(bonusBallNumber)) {
            throw new IllegalArgumentException(ExceptionMessage.LOTTO_AND_BONUS_BALL_DUPLICATION);
        }
    }

    public List<LottoNumber> getWinningLotto() {
        return winningLotto.getLotto();
    }

    public LottoNumber getBonusBall() {
        return bonusBall;
    }
}
