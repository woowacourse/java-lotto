package domain;

import java.util.ArrayList;
import java.util.List;

public class Lotto {
    private static final int LOTTO_BALLS_NUMBER = 6;

    private final List<LottoBall> lotto;

    public Lotto(final List<LottoBall> lotto) {
        validateLotto(lotto);
        this.lotto = new ArrayList<>(lotto);
    }

    private void validateLotto(final List<LottoBall> lotto) {
        validateLottoSize(lotto);
        validateDuplicate(lotto);
    }

    private void validateLottoSize(final List<LottoBall> lotto) {
        if (lotto.size() != LOTTO_BALLS_NUMBER) {
            throw new IllegalArgumentException(LOTTO_BALLS_NUMBER + "개의 로또 번호가 필요합니다.");
        }
    }

    private void validateDuplicate(final List<LottoBall> lotto) {
        final int lottoSizeWithoutDuplicate = (int)lotto.stream().distinct().count();

        if (lotto.size() != lottoSizeWithoutDuplicate) {
            throw new IllegalArgumentException("로또 번호가 중복됩니다");
        }
    }

    public boolean contains(final LottoBall lottoBall) {
        return lotto.contains(lottoBall);
    }
}
