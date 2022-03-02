package lotto.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Lotto {
    public static final int BALL_COUNT = 6;
    public static final int LOTTO_PRICE = 1000;
    private static final String ERROR_BALL_COUNT = BALL_COUNT + "개의 숫자를 입력해주세요";
    private static final String ERROR_DUPLICATED_NUMBER = "번호가 중복됩니다!";

    private final List<Ball> lotto = new ArrayList<>();

    public Lotto(final List<Ball> lotto) {
        validateLotto(lotto);
        this.lotto.addAll(lotto);
    }

    public List<Ball> getLottoBalls() {
        return lotto;
    }

    public boolean contains(final Ball number) {
        return lotto.contains(number);
    }

    private void validateLotto(final List<Ball> lotto) {
        validateDuplicatedNumber(lotto);
        validateLottoCount(lotto);
    }

    private void validateDuplicatedNumber(final List<Ball> lotto) {
        List<Ball> distinct = lotto.stream()
                .distinct()
                .collect(Collectors.toList());
        if (distinct.size() != lotto.size()) {
            throw new IllegalArgumentException(ERROR_DUPLICATED_NUMBER);
        }
    }

    private void validateLottoCount(final List<Ball> lotto) {
        if (lotto.size() != BALL_COUNT) {
            throw new IllegalArgumentException(ERROR_BALL_COUNT);
        }
    }
}
