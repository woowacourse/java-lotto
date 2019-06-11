package lotto.domain.game;

public class ResultCounter {
    private static final int INIT_STATE = 0;
    private static final int RESULT_COUNT_UNIT = 1;

    private int counter;

    public ResultCounter() {
        this(INIT_STATE);
    }

<<<<<<< MrKwon
    public int getCounter() {
        return counter;
    }

=======
>>>>>>> MrKwon
    private ResultCounter(int counter) {
        this.counter = counter;
    }

<<<<<<< MrKwon
    public void increase() {
        this.counter += RESULT_COUNT_UNIT;
=======
    public ResultCounter increase() {
        return new ResultCounter(this.counter + RESULT_COUNT_UNIT);
>>>>>>> MrKwon
    }

    public int multiply(int prize) {
        return prize * counter;
    }

    @Override
    public String toString() {
        return String.valueOf(counter);
    }
}
