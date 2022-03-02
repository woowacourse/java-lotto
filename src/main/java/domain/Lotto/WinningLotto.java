package domain.Lotto;

import domain.LottoGenerator.CustomLottoGenerator;
import domain.LottoGenerator.LottoGenerator;
import domain.Result;
import utils.ExceptionMessage;

import java.util.List;

public class WinningLotto {

    private Lotto winningLotto;
    private LottoNumber bonusBall;

    public WinningLotto(List<Integer> winningNumber, int bonusBallNumber) {
        validateDuplicate(winningNumber, bonusBallNumber);
        Lotto winningLotto = determineWinningLotto(winningNumber);
        this.winningLotto = winningLotto;
        this.bonusBall = new LottoNumber(bonusBallNumber);
    }

    private void validateDuplicate(List<Integer> numbers, int bonusBallNumber) {
        if (numbers.contains(bonusBallNumber)) {
            throw new IllegalArgumentException(ExceptionMessage.LOTTO_AND_BONUS_BALL_DUPLICATION);
        }
    }

    private Lotto determineWinningLotto(List<Integer> winningNumber) {
        LottoGenerator lottoGenerator = new CustomLottoGenerator();
        Lotto winningLotto = lottoGenerator.generateLotto(winningNumber);
        return winningLotto;
    }

    public Result judge(Lotto lotto) {
        int hitCount = lotto.compareLotto(winningLotto);
        boolean isHitBonusBall = lotto.compareBonusBall(bonusBall);
        return new Result(hitCount, isHitBonusBall);
    }
}