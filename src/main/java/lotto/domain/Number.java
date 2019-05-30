package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Number implements Comparable<Number> {
    static final int MAX_NUMBER = 45;
    static final int MIN_NUMBER = 1;

    private static final List<Number> NUMBER_LIST = new ArrayList<>();

    static {
        for (int i = MIN_NUMBER; i <= MAX_NUMBER; i++) {
            NUMBER_LIST.add(new Number(i));
        }
    }

    private final int num;

    private Number(final int num) {
        validate(num);
        this.num = num;
    }

    private void validate(final int num) {
        if (num < MIN_NUMBER || num > MAX_NUMBER) {
            throw new IllegalArgumentException("유효한 번호가 아닙니다.");
        }
    }

    public static Number valueOf(int num){
        return NUMBER_LIST.get(num);
    }

    public int getNum() {
        return num;
    }

    public static List<Number> getNumberList() {
        return NUMBER_LIST;
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
}
