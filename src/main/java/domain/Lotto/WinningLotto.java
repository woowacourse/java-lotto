package domain.Lotto;

<<<<<<< HEAD
<<<<<<< HEAD
=======
import domain.Lotto.Lotto;
import domain.Lotto.LottoNumber;
>>>>>>> d5f0ef8 (refactor: 패키지 분리)
=======
>>>>>>> c348e4d (refactor : 당첨 로또와 사용자 로또 비교하는 메서드와, 보너스볼과 사용자 로또 비교해주는 메서드 위치 변경)
import utils.ExceptionMessage;

import java.util.List;

public class WinningLotto {

<<<<<<< HEAD
<<<<<<< HEAD
    private final Lotto winningLotto;
    private final LottoNumber bonusBall;
=======
    private Lotto winningLotto;
    private LottoNumber bonusBall;
>>>>>>> d5f0ef8 (refactor: 패키지 분리)
=======
    private final Lotto winningLotto;
    private final LottoNumber bonusBall;
>>>>>>> c348e4d (refactor : 당첨 로또와 사용자 로또 비교하는 메서드와, 보너스볼과 사용자 로또 비교해주는 메서드 위치 변경)

    public WinningLotto(Lotto winningLotto, LottoNumber bonusBallNumber) {
        validateDuplicate(winningLotto, bonusBallNumber);
        this.winningLotto = winningLotto;
        bonusBall = bonusBallNumber;
    }

<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> c348e4d (refactor : 당첨 로또와 사용자 로또 비교하는 메서드와, 보너스볼과 사용자 로또 비교해주는 메서드 위치 변경)
    public boolean isContainLottoNumber(LottoNumber lottoNumber) {
        List<LottoNumber> winningLottoNumbers = winningLotto.getLotto();
        return winningLottoNumbers.contains(lottoNumber);
    }

    public boolean isContainBonusBall(List<LottoNumber> lottoNumbers) {
        return lottoNumbers.contains(bonusBall);
    }

<<<<<<< HEAD
=======
>>>>>>> d5f0ef8 (refactor: 패키지 분리)
=======
>>>>>>> c348e4d (refactor : 당첨 로또와 사용자 로또 비교하는 메서드와, 보너스볼과 사용자 로또 비교해주는 메서드 위치 변경)
    public void validateDuplicate(List<LottoNumber> numbers, LottoNumber bonusBallNumber) {
=======
    private void validateDuplicate(List<LottoNumber> numbers, LottoNumber bonusBallNumber) {
>>>>>>> 4022ea6 (refactor: 메서드 위치 변경)
        if (numbers.contains(bonusBallNumber)) {
=======
    private void validateDuplicate(Lotto winningLotto, LottoNumber bonusBallNumber) {
        if (winningLotto.isContainLottoNumber(bonusBallNumber)) {
>>>>>>> 68b3f92 (feat : Lotto 클래스에 lottoNumber를 포함 여부를 보여주는 메서드 추가)
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
