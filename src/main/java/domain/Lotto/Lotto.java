package domain.Lotto;

import domain.Rank;
import utils.ExceptionMessage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lotto {

    private static final int CORRECT_LOTTO_SIZE = 6;

    private final List<LottoNumber> lotto;

    public Lotto(List<LottoNumber> lotto) {
        validateLottoSize(lotto);
        this.lotto = new ArrayList<>(lotto);
    }

    private void validateLottoSize(List<LottoNumber> lotto) {
        if (lotto.size() != CORRECT_LOTTO_SIZE) {
            throw new IllegalArgumentException(ExceptionMessage.LOTTO_SIZE_IS_NOT_SIX);
        }
    }

    public Rank compare(WinningLotto winningLotto) {
        int hitCount = (int) lotto.stream()
                .filter(winningLotto::isContainLottoNumber)
                .count();

        boolean isHitBonusBall = winningLotto.isContainBonusBall(lotto);
        return Rank.judgeResult(hitCount, isHitBonusBall);
    }

    public boolean hasLottoNumber(LottoNumber lottoNumber) {
        return lotto.contains(lottoNumber);
    }

    public List<LottoNumber> getLotto() {
        return Collections.unmodifiableList(lotto);
    }
}

