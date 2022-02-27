package domain.Lotto;

import domain.Result;
import utils.ExceptionMessage;

import java.util.List;
import java.util.Objects;

public class Lotto {

    public static final int CORRECT_LOTTO_SIZE = 6;

    private final List<LottoNumber> lotto;

    public Lotto(List<LottoNumber> lotto) {
        validateLottoSize(lotto);
        validateDuplicate(lotto);
        this.lotto = lotto;
    }

    private void validateLottoSize(List<LottoNumber> lotto) {
        if (lotto.size() != CORRECT_LOTTO_SIZE) {
            throw new IllegalArgumentException(ExceptionMessage.LOTTO_SIZE_IS_NOT_SIX);
        }
    }

    private void validateDuplicate(List<LottoNumber> lotto){
        if(lotto.stream().distinct().count() != lotto.size()){
            throw new IllegalArgumentException(ExceptionMessage.DUPLICATE_LOTTO_NUMBER);
        }
    }

    public List<LottoNumber> getLotto() {
        return lotto;
    }

    public int compareLotto(Lotto winningLotto){
        int hitCount = 0;
        for (LottoNumber lottoNumber : lotto) {
           hitCount += lottoNumber.checkHit(winningLotto.lotto);
        }
        return hitCount;
    }

    public boolean compareBonusBall(LottoNumber bonusBall) {
        return lotto.contains(bonusBall);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lotto lotto1 = (Lotto) o;
        return Objects.equals(lotto, lotto1.lotto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lotto);
    }
}
