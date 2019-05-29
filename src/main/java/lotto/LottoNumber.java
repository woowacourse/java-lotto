package lotto;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

class LottoNumber implements Comparable<LottoNumber> {
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;
    private static final Map<Integer, LottoNumber> LOTTO_NUMBERS;

    static {
        LOTTO_NUMBERS = new HashMap<>();
        for (int i = MIN_NUMBER; i <= MAX_NUMBER; i++) {
            LOTTO_NUMBERS.put(i, new LottoNumber(i));
        }
    }

    private final int lottoNumber;

    private LottoNumber(final int lottoNumber) {
        this.lottoNumber = lottoNumber;
    }

    static LottoNumber of(final int lottoNumber) {
        Optional<LottoNumber> optLottoNumber = Optional.ofNullable(LOTTO_NUMBERS.get(lottoNumber));
        return optLottoNumber.orElseThrow(() ->
                new LottoNumberCreateException(MIN_NUMBER + "~" + MAX_NUMBER + " 사이의 숫자를 입력하세요")
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LottoNumber)) return false;
        LottoNumber that = (LottoNumber) o;
        return (lottoNumber == that.lottoNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoNumber);
    }

    @Override
    public int compareTo(LottoNumber o) {
        return Integer.compare(this.lottoNumber, o.lottoNumber);
    }
}
