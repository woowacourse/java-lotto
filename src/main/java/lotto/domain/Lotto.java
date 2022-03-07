package lotto.domain;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Lotto {
    public static final int BALL_COUNT = 6;
    public static final int LOTTO_PRICE = 1000;
    private static final String ERROR_BALL_COUNT = BALL_COUNT + "개의 숫자를 입력해주세요";
    private static final String ERROR_DUPLICATED_NUMBER = "번호가 중복됩니다!";

    private final Set<Ball> lotto = new TreeSet<>();

    private Lotto(final Set<Ball> lotto) {
        this.lotto.addAll(lotto);
    }

    public static Lotto from(final List<Ball> lotto) {
        validateLotto(lotto);
        return new Lotto(new TreeSet<>(lotto));
    }

    public Set<Ball> getLottoBalls() {
        return lotto;
    }

    public boolean contains(final Ball number) {
        return lotto.contains(number);
    }

    private static void validateLotto(final List<Ball> lotto) {
        validateDuplicatedNumber(lotto);
        validateLottoCount(lotto);
    }

    private static void validateDuplicatedNumber(final List<Ball> lotto) {
        List<Ball> distinct = lotto.stream()
                .distinct()
                .collect(Collectors.toList());
        if (distinct.size() != lotto.size()) {
            throw new IllegalArgumentException(ERROR_DUPLICATED_NUMBER);
        }
    }

    private static void validateLottoCount(final List<Ball> lotto) {
        if (lotto.size() != BALL_COUNT) {
            throw new IllegalArgumentException(ERROR_BALL_COUNT);
        }
    }
}
