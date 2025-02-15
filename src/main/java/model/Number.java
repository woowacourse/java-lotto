package model;

public record Number(int value) implements Comparable<Number> {

    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int MAX_LOTTO_NUMBER = 45;

    public Number {
        if (value < MIN_LOTTO_NUMBER || value > MAX_LOTTO_NUMBER) {
            throw new IllegalArgumentException(String.format("로또 번호는 %d 이상 %d 이하여야 합니다.",
                    MIN_LOTTO_NUMBER,
                    MAX_LOTTO_NUMBER
            ));
        }
    }

    @Override
    public int compareTo(Number o) {
        return this.value - o.value;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
