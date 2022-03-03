package domain.lotto;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

public class Lotto {
    private static final String LOTTO_NUMS_DUPLICATED_ERROR_MESSAGE = "로또 번호는 중복될 수 없습니다.";
    private static final int LOTTO_NUMS_SIZE = 6;
    private static final String LOTTO_NUMS_SIZE_ERROR_MESSAGE = String.format("로또 번호는 %d개로 이루어져야 합니다.",
            LOTTO_NUMS_SIZE);

    private final List<LottoNumber> lotto;

    private Lotto(List<LottoNumber> balls) {
        validate(balls);
        Collections.sort(balls);
        this.lotto = balls;
    }

    public static Lotto from(List<LottoNumber> lotto) {
        return new Lotto(lotto);
    }

    private static void validate(List<LottoNumber> lottoNumbers) {
        HashSet<LottoNumber> compareNums = new HashSet<>(lottoNumbers);
        if (compareNums.size() != lottoNumbers.size()) {
            throw new IllegalArgumentException(LOTTO_NUMS_DUPLICATED_ERROR_MESSAGE);
        }
        if (lottoNumbers.size() != LOTTO_NUMS_SIZE) {
            throw new IllegalArgumentException(LOTTO_NUMS_SIZE_ERROR_MESSAGE);
        }
    }

    public int countSameNum(final WinNumbers winNumbers) {
        return (int) lotto.stream()
                .filter(winNumbers::contains)
                .count();
    }

    public boolean contains(final LottoNumber ball) {
        return lotto.contains(ball);
    }

    public List<LottoNumber> get() {
        return lotto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Lotto lotto1 = (Lotto) o;
        return Objects.equals(lotto, lotto1.lotto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lotto);
    }

    @Override
    public String toString() {
        return "Lotto{" +
                "lotto=" + lotto +
                '}';
    }
}/**/