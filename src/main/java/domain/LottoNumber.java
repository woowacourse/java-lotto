package domain;

import java.util.Objects;

public class LottoNumber implements Comparable<LottoNumber> {
    private static final String REQUEST_1_TO_45_NUMBER = "1부터 45 사이의 수를 입력해주세요.";
    private final int number;
    
    public LottoNumber(int number) {
        checkLottoNumberRange(number);
        this.number = number;
    }

    private void checkLottoNumberRange(int number) {
        if (number < 1 || number > 45) {
            throw new IllegalArgumentException(REQUEST_1_TO_45_NUMBER);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        LottoNumber that = (LottoNumber)o;
        return number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public int compareTo(LottoNumber o) {
        return number - o.number;
    }

    public int getNumber() {
        return number;
    }
}
