package lotto.domain.game;

public class ResultCounter {
    private static final int INIT_STATE = 0;

    private int counter;

    ResultCounter() {
        this(INIT_STATE);
    }

    private ResultCounter(int counter) {
        this.counter = counter;
    }

    void increase() {
        this.counter += 1;
    }

    public int multiply(int prize) {
        return prize * counter;
    }

    @Override
    public String toString() {
        return String.valueOf(counter);
    }
}
