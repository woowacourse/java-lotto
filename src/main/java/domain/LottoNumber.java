package domain;

import java.util.Objects;

public class LottoNumber implements Comparable<LottoNumber>{
    private static final String ERROR_BOUND_MESSAGE = "1부터 45 사이의 숫자만 입력 가능합니다.";

    private int number;

    public LottoNumber(int number) {
        if (number < 1 || number > 45) {
            throw new IllegalArgumentException(ERROR_BOUND_MESSAGE);
        }
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoNumber that = (LottoNumber) o;
        return number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public int compareTo(LottoNumber o) {
        return Integer.compare(number,o.number);
    }
}
