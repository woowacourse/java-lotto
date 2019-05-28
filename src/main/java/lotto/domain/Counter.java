package lotto.domain;

public class Counter {
    private int counter;

    private Counter() {
        this(0);
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
