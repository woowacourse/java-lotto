package model;

import java.util.Collections;
import java.util.List;

public class Lotto {
    public static final String INVALID_LOTTO_ERROR_MESSAGE = "로또 번호는 중복되지않는 1~45 범위의 6자리 숫자이여야 합니다.";
    public static final int LOTTO_SIZE = 6;
    private final List<Integer> lotto;

    public Lotto(final List<Integer> lotto) {
        validateLotto(lotto);
        this.lotto = lotto;
    }

    public List<Integer> getLotto() {
        return Collections.unmodifiableList(lotto);
    }

    private void validateLotto(final List<Integer> lotto) {
        validateLottoSize(lotto);
    }

    private void validateLottoSize(final List<Integer> lotto) {
        if (lotto.size() != 6) {
            throw new IllegalArgumentException(INVALID_LOTTO_ERROR_MESSAGE);
        }
    }

    private void validateLottoNumberRange(final List<Integer> lotto) {

    }

    private void validateLottoNumberNotDuplicated(final List<Integer> lotto) {

    }
}
