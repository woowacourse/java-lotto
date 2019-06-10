package lotto.domain;

import java.util.*;

public class Number implements Comparable<Number> {
    static final int MAX_NUMBER = 45;
    static final int MIN_NUMBER = 1;

    private static final Map<Integer, Number> NUMBER_MAP = new HashMap<>();

    static {
        for (int i = MIN_NUMBER; i <= MAX_NUMBER; i++) {
            NUMBER_MAP.put(i, new Number(i));
        }
    }

    private final int num;

    private Number(final int num) {
        validate(num);
        this.num = num;
    }

    private static void validate(final int num) {
        if (num < MIN_NUMBER || num > MAX_NUMBER) {
            throw new IllegalArgumentException("유효한 번호가 아닙니다.");
        }
    }

    public static Number valueOf(int num){
        if (NUMBER_MAP.get(num) == null) {
            return new Number(num);
        }
        return NUMBER_MAP.get(num);
    }

    public int getNum() {
        return num;
    }

    public static List<Number> getNumberList() {
        return new ArrayList<>(NUMBER_MAP.values());
    }

    @Override
    public String toString() {
        return String.valueOf(num);
    }

    @Override
    public int compareTo(Number number) {
        if (this.num < number.getNum()) {
            return -1;
        }
        if (this.num == number.getNum()) {
            return 0;
        }
        return 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Number number = (Number) o;
        return num == number.num;
    }

    @Override
    public int hashCode() {
        return Objects.hash(num);
    }
}
