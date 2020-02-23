package domain;

import java.util.Objects;

public class LottoNumber {
    private int lottoNumber;

    public LottoNumber(int number) {
        validateLottoNumberRange(number);
        this.lottoNumber = number;
    }

    private void validateLottoNumberRange(int number) {
        if (number < 1 || number > 45) {
            throw new IllegalArgumentException("범위를 벗어나는 로또 번호 입니다.");
        }
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
        return this.lottoNumber == that.lottoNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.lottoNumber);
    }
}
