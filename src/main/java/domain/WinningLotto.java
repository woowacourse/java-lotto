package domain;

import utils.ExceptionMessage;

import java.util.List;

public class WinningLotto {

    private Lotto winningLotto;
    private LottoNumber bonusBall;

    public WinningLotto(List<LottoNumber> numbers, LottoNumber bonusBallNumber){
        validateDuplicate(numbers, bonusBallNumber);
        winningLotto = new Lotto(numbers);
        bonusBall = bonusBallNumber;
    }

    public void validateDuplicate(List<LottoNumber> numbers, LottoNumber bonusBallNumber){
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
