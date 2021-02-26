package lotto.domain.lotto;

import java.util.Objects;

public class LottoNumber implements Comparable<LottoNumber> {
    private static final String ERROR_LOTTO_NUMBER_OUT_OF_BOUND = "[Error] 로또 번호는 1부터 45까지 입니다.";

    protected static final int LOTTO_START_NUMBER = 1;
    protected static final int LOTTO_FINAL_NUMBER = 45;

    private final int value;

    public LottoNumber(int lottoNumber) {
        validateLottoNumberRange(lottoNumber);
        this.value = lottoNumber;
    }

    private void validateLottoNumberRange(int lottoNumber) {
        if (lottoNumber < LOTTO_START_NUMBER || lottoNumber > LOTTO_FINAL_NUMBER) {
            throw new IllegalArgumentException(ERROR_LOTTO_NUMBER_OUT_OF_BOUND);
        }
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
