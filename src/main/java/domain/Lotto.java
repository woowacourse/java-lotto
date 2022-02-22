package domain;

import java.util.List;

public class Lotto {

    public static final String LOTTO_SIZE_IS_NOT_SIX = "로또 사이즈는 6이여야 합니다.";
    public static final int CORRECT_LOTTO_SIZE = 6;

    private final List<LottoNumber> lotto;

    public Lotto(List<LottoNumber> lotto) {
        validateLottoSize(lotto);
        this.lotto = lotto;
    }

    private void validateLottoSize(List<LottoNumber> lotto){
        if(lotto.size() != CORRECT_LOTTO_SIZE){
            throw new IllegalArgumentException(LOTTO_SIZE_IS_NOT_SIX);
        }
    }

    public List<LottoNumber> getLotto() {
        return lotto;
    }
}
