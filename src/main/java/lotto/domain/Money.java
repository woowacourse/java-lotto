package lotto.domain;

public class Money {

    private final int value;

    public Money(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("[Error] 돈은 0보다 커야 합니다.");
        }
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
