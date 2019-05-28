package lotto.domain;

public class Counter {
    private static final int INIT_STATE = 0;

    private int counter;

    private Counter() {
        this(INIT_STATE);
    }

    private Counter(int counter) {
        this.counter = counter;
    }

    public static Counter create() {
        return new Counter();
    }

    public Counter increase() {
        return new Counter(this.counter + 1);
    }

    @Override
    public String toString() {
        return String.valueOf(counter);
    }
}
