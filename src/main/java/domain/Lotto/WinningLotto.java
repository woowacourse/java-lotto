package domain.Lotto;

import domain.Lotto.Lotto;
import domain.Lotto.LottoNumber;
import domain.Result;
import utils.ExceptionMessage;

import java.util.List;

public class WinningLotto {

    private Lotto winningLotto;
    private LottoNumber bonusBall;

    public WinningLotto(Lotto winningLotto, LottoNumber bonusBallNumber) {
        validateDuplicate(winningLotto.getLotto(), bonusBallNumber);
        this.winningLotto = winningLotto;
        bonusBall = bonusBallNumber;
    }

    private void validateDuplicate(List<LottoNumber> numbers, LottoNumber bonusBallNumber) {
        if (numbers.contains(bonusBallNumber)) {
            throw new IllegalArgumentException(ExceptionMessage.LOTTO_AND_BONUS_BALL_DUPLICATION);
        }
    }

    public Result judge(Lotto lotto) {
        int hitCount = lotto.compareLotto(winningLotto);
        boolean isHitBonusBall = lotto.compareBonusBall(bonusBall);
        return new Result(hitCount, isHitBonusBall);
    }
}