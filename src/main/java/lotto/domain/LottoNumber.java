package lotto.domain;

import java.util.Objects;

public class LottoNumber implements Comparable<LottoNumber>{
    private final int number;

    public LottoNumber(int number) {
        validateNumberRange(number);
        this.number = number;
    }

    private static void validateNumberRange(int number) {
        if (isLessThanMin(number) || isGreaterThanMax(number)) {
            throw new IllegalArgumentException("로또 숫자는 1~45사이어야 합니다.");
        }
    }

    private static boolean isGreaterThanMax(int number) {
        return number > 45;
    }

    private static boolean isLessThanMin(int number) {
        return number < 1;
    }

    @Override
    public int compareTo(LottoNumber anotherNumber) {
        return this.number - anotherNumber.number;
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
    public String toString() {
        return String.valueOf(number);
    }
}
