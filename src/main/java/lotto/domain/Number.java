package lotto.domain;

public class Number implements Comparable<Number> {
    static final int MAX_NUMBER = 45;
    static final int MIN_NUMBER = 1;

    private final int num;

    public Number(final int num) {
        validate(num);
        this.num = num;
    }

    private void validate(final int num) {
        if (num < MIN_NUMBER || num > MAX_NUMBER) {
            throw new IllegalArgumentException("유효한 번호가 아닙니다.");
        }
    }

    public int getNum() {
        return num;
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
