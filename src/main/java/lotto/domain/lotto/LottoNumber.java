package lotto.domain.lotto;

import static lotto.view.messages.ErrorMessages.LOTTO_NUMBER_RANGE_ERROR;

import java.util.Objects;

public class LottoNumber implements Comparable<LottoNumber> {

    public static final int LOTTO_NUMBER_START = 1;
    public static final int LOTTO_NUMBER_END = 45;
    private final int value;

    public LottoNumber(int value) {
        if (value < LOTTO_NUMBER_START || value > LOTTO_NUMBER_END) {
            throw new IllegalArgumentException(LOTTO_NUMBER_RANGE_ERROR.getMessage());
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
