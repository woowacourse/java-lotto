package lotto.domain.game;

public class ResultCounter {
    private static final int INIT_STATE = 0;

    private int counter;

    private ResultCounter() {
        this(INIT_STATE);
    }

    private ResultCounter(int counter) {
        this.counter = counter;
    }

    public static ResultCounter create() {
        return new ResultCounter();
    }

    public ResultCounter increase() {
        return new ResultCounter(this.counter + 1);
    }

    public int multiply(int prize) {
        return prize * counter;
    }

    @Override
    public String toString() {
        return String.valueOf(counter);
    }

}
