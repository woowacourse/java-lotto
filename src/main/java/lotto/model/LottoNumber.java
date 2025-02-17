package lotto.model;

import static lotto.constant.LottoNumberConstants.LOTTO_NUMBER_MAX;
import static lotto.constant.LottoNumberConstants.LOTTO_NUMBER_MIN;

import java.util.Objects;

public class LottoNumber {

    private final int number;

    public LottoNumber(int number) {
        validateNumberInRange(number);
        this.number = number;
    }

    private void validateNumberInRange(int number) {
        if (number < LOTTO_NUMBER_MIN.value() || number > LOTTO_NUMBER_MAX.value()) {
            throw new IllegalArgumentException(
                    String.format("로또 번호는 %d부터 %d 사이의 수여야 합니다.",
                            LOTTO_NUMBER_MIN.value(), LOTTO_NUMBER_MAX.value()));
        }
    }

    public int getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        LottoNumber inputNumber = (LottoNumber) object;
        return number == inputNumber.number;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(number);
    }
}
