package lotto.domain.ticket;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LottoNumber implements Comparable<LottoNumber> {
    public static final int MINIMUM = 1;
    public static final int MAXIMUM = 45;
    private static final Map<Integer, LottoNumber> lottoNumbers = new HashMap<>();
    static {
        for(int i = MINIMUM; i <= MAXIMUM; i++) {
            lottoNumbers.put(i, new LottoNumber(i));
        }
    }

    private final int number;

    private LottoNumber(int value) {
        this.number = value;
    }

    public static LottoNumber of(int number) {
        LottoNumber lottoNumber = lottoNumbers.get(number);
        if (lottoNumber == null) {
            throw new IllegalArgumentException(String.format("로또 번호는 %d 이상, %d 이하여야 합니다.", MINIMUM, MAXIMUM));
        }
        return lottoNumber;
    }

    public int getNumber() {
        return this.number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LottoNumber)) {
            return false;
        }
        LottoNumber that = (LottoNumber) o;
        return number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public int compareTo(LottoNumber o) {
        return this.number - o.number;
    }
}
