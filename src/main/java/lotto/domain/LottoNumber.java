package lotto.domain;

import java.util.Objects;

public class LottoNumber implements Comparable<LottoNumber> {
    public static final int MIN_BOUND = 1;
    public static final int MAX_BOUND = 45;

    private final int number;

    public LottoNumber(int number) {
        checkRange(number);
        this.number = number;
    }

    private static void checkRange(int number) {
        if (MIN_BOUND > number || MAX_BOUND < number) {
            throw new IllegalArgumentException("[ERROR] 1부터 45까지의 번호로 입력해주세요.");
        }
    }

    public int getNumber() {
        return number;
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
        return number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public int compareTo(LottoNumber o) {
        return Integer.compare(this.number, o.number);
    }

    @Override
    public String toString() {
        return String.valueOf(number);
    }
}
