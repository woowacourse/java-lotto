package lotto.domain;

public class Counter {
    private final int counter;

    private Counter() {
        this(0);
    }

    private Counter(int counter) {
        this.counter = counter;
    }

    public static Counter create() {
        return new Counter();
    }

    public static Counter increase(Counter counter) {
        return new Counter(counter.counter + 1);
    }
}
