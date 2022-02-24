package domain.Lotto;

<<<<<<< HEAD
=======
import domain.Lotto.Lotto;
import domain.Lotto.LottoNumber;
>>>>>>> d5f0ef8 (refactor: 패키지 분리)
import utils.ExceptionMessage;

import java.util.List;

public class WinningLotto {

<<<<<<< HEAD
    private final Lotto winningLotto;
    private final LottoNumber bonusBall;
=======
    private Lotto winningLotto;
    private LottoNumber bonusBall;
>>>>>>> d5f0ef8 (refactor: 패키지 분리)

    public WinningLotto(Lotto winningLotto, LottoNumber bonusBallNumber) {
        validateDuplicate(winningLotto.getLotto(), bonusBallNumber);
        this.winningLotto = winningLotto;
        bonusBall = bonusBallNumber;
    }

<<<<<<< HEAD
    public boolean isContainLottoNumber(LottoNumber lottoNumber) {
        List<LottoNumber> winningLottoNumbers = winningLotto.getLotto();
        return winningLottoNumbers.contains(lottoNumber);
    }

    public boolean isContainBonusBall(List<LottoNumber> lottoNumbers) {
        return lottoNumbers.contains(bonusBall);
    }

=======
>>>>>>> d5f0ef8 (refactor: 패키지 분리)
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
