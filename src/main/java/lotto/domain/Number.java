package lotto.domain;

import lotto.NumberValidException;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Number {
    public static final int MIN_LOTTO_NUMBER = 1;
    public static final int MAX_LOTTO_NUMBER = 45;
    private static Map<Integer,Number> numbers = new HashMap<>();
    private int number;

    private Number(int number) {
        if (number >= MAX_LOTTO_NUMBER || number < MIN_LOTTO_NUMBER) {
            throw new NumberValidException("로또번호에 해당되지 않는 숫자입니다.");
        }
        this.number = number;
    }

    public static Number of(int number) {
        if(!numbers.containsKey(number)) {
            numbers.put(number,new Number(number));
        }
        return numbers.get(number);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Number number1 = (Number) o;
        return number == number1.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public String toString() {
        return ""+number;
    }
}
