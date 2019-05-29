package lotto.domain;

import java.util.Objects;

public abstract class Number implements Comparable<Number> {
    private final int number;

    protected Number(int number) {
        valid(number);
        this.number = number;
    }

    public abstract void valid(int lottoNumber);

    public String getNumber() {
        return String.valueOf(number);
    }

    @Override
    public int compareTo(Number o) {
        return Integer.compare(this.number, o.number);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Number that = (Number) o;
        return number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
