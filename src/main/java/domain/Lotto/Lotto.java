package domain.Lotto;

import domain.Result;
import utils.ExceptionMessage;

<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 1b78799 (refactor : Dto 생성자 private 으로 접근지정자 변경)
import java.util.ArrayList;
import java.util.Collections;
=======
>>>>>>> d5f0ef8 (refactor: 패키지 분리)
=======
import java.util.Collections;
>>>>>>> 3158ddf (refactor : 일급 컬렉션의 List 불변 객체로 만들기)
import java.util.List;

public class Lotto {

    private static final int CORRECT_LOTTO_SIZE = 6;

    private final List<LottoNumber> lotto;

    public Lotto(List<LottoNumber> lotto) {
        validateLottoSize(lotto);
<<<<<<< HEAD
<<<<<<< HEAD
        this.lotto = new ArrayList<>(lotto);
=======
        this.lotto = lotto;
>>>>>>> d5f0ef8 (refactor: 패키지 분리)
=======
        this.lotto = new ArrayList<>(lotto);
>>>>>>> 1b78799 (refactor : Dto 생성자 private 으로 접근지정자 변경)
    }

    private void validateLottoSize(List<LottoNumber> lotto) {
        if (lotto.size() != CORRECT_LOTTO_SIZE) {
            throw new IllegalArgumentException(ExceptionMessage.LOTTO_SIZE_IS_NOT_SIX);
        }
    }

<<<<<<< HEAD
<<<<<<< HEAD
    public Result judge(WinningLotto winningLotto) {
        int hitCount = (int) lotto.stream()
                .filter(winningLotto::isContainLottoNumber)
                .count();

        boolean isHitBonusBall = winningLotto.isContainBonusBall(lotto);
        return new Result(hitCount, isHitBonusBall);
    }

    public List<LottoNumber> getLotto() {
        return Collections.unmodifiableList(lotto);
=======
    public List<LottoNumber> getLotto() {
        return lotto;
    }

=======
>>>>>>> 3158ddf (refactor : 일급 컬렉션의 List 불변 객체로 만들기)
    public Result judge(WinningLotto winningLotto) {
        int hitCount = (int) lotto.stream()
                .filter(winningLotto::isContainLottoNumber)
                .count();

<<<<<<< HEAD
    private boolean judgeBonusBall(LottoNumber bonusBall) {
        return lotto.contains(bonusBall);
>>>>>>> d5f0ef8 (refactor: 패키지 분리)
=======
        boolean isHitBonusBall = winningLotto.isContainBonusBall(lotto);
        return new Result(hitCount, isHitBonusBall);
>>>>>>> c348e4d (refactor : 당첨 로또와 사용자 로또 비교하는 메서드와, 보너스볼과 사용자 로또 비교해주는 메서드 위치 변경)
    }

    public boolean isContainLottoNumber(LottoNumber lottoNumber) {
        return lotto.contains(lottoNumber);
    }

    public List<LottoNumber> getLotto() {
        return Collections.unmodifiableList(lotto);
    }
}
