package model;

import java.util.Collections;
import java.util.List;

public class Lotto {
    public static final String INVALID_LOTTO_ERROR_MESSAGE = "로또 번호는 중복되지않는 1~45 범위의 6자리 숫자이여야 합니다.";

    public static final int LOTTO_SIZE = 6;
    public static final int MINIMUM_LOTTO_NUMBER = 1;
    public static final int MAXIMUM_LOTTO_NUMBER = 45;

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
        validateLottoNumberRange(lotto);
        validateLottoNumberNotDuplicated(lotto);
    }

    private void validateLottoSize(final List<Integer> lotto) {
        if (lotto.size() != 6) {
            throw new IllegalArgumentException(INVALID_LOTTO_ERROR_MESSAGE);
        }
    }

    private void validateLottoNumberRange(final List<Integer> lotto) {
        lotto.stream()
                .filter(number -> number < MINIMUM_LOTTO_NUMBER || number > MAXIMUM_LOTTO_NUMBER)
                .findAny()
                .ifPresent((number) -> {
                    throw new IllegalArgumentException(INVALID_LOTTO_ERROR_MESSAGE);
                });
    }

    private void validateLottoNumberNotDuplicated(final List<Integer> lotto) {
        if (lotto.size() != lotto.stream().distinct().count()) {
            throw new IllegalArgumentException(INVALID_LOTTO_ERROR_MESSAGE);
        }
    }
}
