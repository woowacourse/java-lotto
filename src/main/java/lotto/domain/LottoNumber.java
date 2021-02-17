package lotto.domain;

import lotto.utils.Validator;

import java.util.Objects;

public class LottoNumber implements Comparable<LottoNumber> {
    private final int value;

    public LottoNumber(String input) {
        Validator.validateLottoNumber(input);
        this.value = Integer.parseInt(input);
    }

    private int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoNumber that = (LottoNumber) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }

    @Override
    public int compareTo(LottoNumber anotherLottoNumber) {
        return Integer.compare(this.getValue(), anotherLottoNumber.getValue());
    }
}
