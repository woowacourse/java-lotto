package lotto.domain.lotto;

import static lotto.domain.lotto.utils.LottoAttributes.LOTTO_NUMBER_END;
import static lotto.domain.lotto.utils.LottoAttributes.LOTTO_NUMBER_START;

import java.util.Objects;

public class LottoNumber implements Comparable<LottoNumber> {

    private final int value;

    public LottoNumber(int value) {
        if (value < LOTTO_NUMBER_START || value > LOTTO_NUMBER_END) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 1부터 45까지 숫자입니다.");
        }
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LottoNumber that = (LottoNumber) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public int compareTo(LottoNumber o) {
        return getValue() - o.getValue();
    }

}
