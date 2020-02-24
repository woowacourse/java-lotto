package lotto.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import lotto.exception.InvalidNumberException;

import static javax.management.Query.value;

public class Number implements Comparable<Number> {
    public static final int MIN_LOTTO_NUMBER = 1;
    public static final int MAX_LOTTO_NUMBER = 45;

    private final int number;

    public Number(String number){
        validate(number);
        this.number = Integer.parseInt(number);
    }

    public Number(int number) {
        this.number = number;
    }

    public static void validate(String value) {
        checkNull(value);
        checkBlank(value);
        checkNumberFormat(value);
        checkRange(value);
    }

    private static void checkNumberFormat(String value) {
        try {
            Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new InvalidNumberException("문자는 사용이 불가능합니다.");
        }
    }

    private static void checkRange(String value) {
        int number = Integer.parseInt(value);
        if (number > MAX_LOTTO_NUMBER || number < MIN_LOTTO_NUMBER) {
            throw new InvalidNumberException("로또 번호는 1에서 45까지만 가능합니다");
        }
    }

    private static void checkBlank(String value) {
        if (value.trim().isEmpty()) {
            throw new InvalidNumberException("공백은 사용이 불가능합니다.");
        }
    }

    private static void checkNull(String value) {
        if (Objects.isNull(value)) {
            throw new InvalidNumberException("Null문자열은 사용이 불가능합니다.");
        }
    }

    @Override
    public String toString() {
        return Integer.toString(number);
    }

    @Override
    public int compareTo(Number o) {
        return Integer.compare(this.number, o.number);
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(number);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Number) {
            return this.number == ((Number) obj).number;
        }
        return false;
    }
}
