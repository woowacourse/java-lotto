package lotto.domain.lotto;

import static lotto.utils.Validation.END;
import static lotto.utils.Validation.START;

import java.util.Objects;
import lotto.utils.Validation;

public class LottoNumber implements Comparable<LottoNumber> {

    private final int value;

    public LottoNumber(String number) {
        if (Validation.isNumeric(number)) {
            throw new IllegalArgumentException();
        }

        int lottoNumber = Integer.parseInt(number);
        validateLottoNumberRange(lottoNumber);
        this.value = lottoNumber;
    }

    public LottoNumber(int lottoNumber) {
        validateLottoNumberRange(lottoNumber);
        this.value = lottoNumber;
    }

    private void validateLottoNumberRange(int lottoNumber) {
        if (lottoNumber < START || lottoNumber > END) {
            throw new IllegalArgumentException("[Error] 로또 번호는 1부터 45까지 입니다.");
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
