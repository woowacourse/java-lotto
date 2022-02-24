package domain.Lotto;

import domain.Result;
import utils.ExceptionMessage;

<<<<<<< HEAD
import java.util.ArrayList;
import java.util.Collections;
=======
>>>>>>> d5f0ef8 (refactor: 패키지 분리)
import java.util.List;

public class Lotto {

    public static final int CORRECT_LOTTO_SIZE = 6;

    private final List<LottoNumber> lotto;

    public Lotto(List<LottoNumber> lotto) {
        validateLottoSize(lotto);
<<<<<<< HEAD
        this.lotto = new ArrayList<>(lotto);
=======
        this.lotto = lotto;
>>>>>>> d5f0ef8 (refactor: 패키지 분리)
    }

    private void validateLottoSize(List<LottoNumber> lotto) {
        if (lotto.size() != CORRECT_LOTTO_SIZE) {
            throw new IllegalArgumentException(ExceptionMessage.LOTTO_SIZE_IS_NOT_SIX);
        }
    }

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

    public Result judge(WinningLotto winningLotto) {
        List<LottoNumber> winningNumbers = winningLotto.getWinningLotto();
        int hitCount = 0;
        for (LottoNumber lottoNumber : lotto) {
            hitCount += lottoNumber.checkHit(winningNumbers);
        }
        boolean isHitBonusBall = judgeBonusBall(winningLotto.getBonusBall());
        return new Result(hitCount, isHitBonusBall);
    }

    private boolean judgeBonusBall(LottoNumber bonusBall) {
        return lotto.contains(bonusBall);
>>>>>>> d5f0ef8 (refactor: 패키지 분리)
    }
}
