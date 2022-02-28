package lotto.model.result;

public class Money {
    private static final int UNIT = 1000;
    private final int number;

    public Money(int number) {
        this.number = number * UNIT;
    }

    public int getNumber() {
        return number;
    }
}
