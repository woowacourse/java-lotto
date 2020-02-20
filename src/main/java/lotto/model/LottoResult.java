package lotto.model;

public enum LottoResult {
    THREE(3, 5000),
    FOUR(4, 50000),
    FIVE(5, 150000),
    FIVE_BONUS(5, 30000000),
    SIX(6, 2000000000);

    private final int prize;
    private final int correct;
    private int count = 0;

    LottoResult(int correct, int prize) {
        this.correct = correct;
        this.prize = prize;
    }

    public void setCount() {
        count++;
    }

    public double prizeResult(int count) {
        return count * this.prize;
    }

    public int getPrize() {
        return prize;
    }

    public int getCorrect() {
        return correct;
    }

    public int getCount() {
        return count;
    }
}
