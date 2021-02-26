package domain.lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Lotto {
    private static final int LOTTO_BALLS_NUMBER = 6;

    private final List<LottoBall> lotto;

    public static Lotto of(final List<Integer> lottoNumber) {
        return new Lotto(lottoNumber.stream()
                .map(number -> LottoBall.valueOf(number))
                .collect(Collectors.toList()));
    }

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
        final int lottoSizeWithoutDuplicate = (int) lotto.stream().distinct().count();

        if (lotto.size() != lottoSizeWithoutDuplicate) {
            throw new IllegalArgumentException("로또 번호가 중복됩니다");
        }
    }

    public boolean contains(final LottoBall lottoBall) {
        return lotto.contains(lottoBall);
    }

    public int matchNumber(final Lotto lottoToCompare) {
        return (int) this.lotto.stream()
                .filter(lottoBall -> lottoToCompare.contains(lottoBall))
                .count();
    }

    public List<LottoBall> getLotto() {
        return Collections.unmodifiableList(lotto);
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
