package lottogame.domain.number;

import java.util.Objects;
import lottogame.utils.RandomUtils;

public class LottoNumber {

    private static final int START_LOTTO_NUMBER = 1;
    private static final int FINISH_LOTTO_NUMBER = 45;

    private final int number;

    public LottoNumber() {
        this(RandomUtils.nextPositiveInt(START_LOTTO_NUMBER, FINISH_LOTTO_NUMBER));
    }

    public LottoNumber(final int number) {
        this.number = number;
        validateNumberRange(this.number);
    }

    private void validateNumberRange(final int value) {
        if (value < START_LOTTO_NUMBER || FINISH_LOTTO_NUMBER < value) {
            throw new IllegalArgumentException("유효한 로또 번호 범위는 "
                + START_LOTTO_NUMBER + "~" + FINISH_LOTTO_NUMBER + "입니다.");
        }
    }

    public int getNumber() {
        return this.number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LottoNumber lottoNumber = (LottoNumber) o;
        return number == lottoNumber.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
