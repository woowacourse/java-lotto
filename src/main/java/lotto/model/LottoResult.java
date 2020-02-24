package lotto.model;

public enum LottoResult {
    THREE(3, 5000),
    FOUR(4, 50000),
    FIVE(5, 150000),
    FIVE_BONUS(5, 30000000),
    SIX(6, 2000000000);

    private final int prize;
    private final int correct;

    LottoResult(int correct, int prize) {
        this.correct = correct;
        this.prize = prize;
    }

    public int getPrize() {
        return prize;
    }

    public int getCorrect() {
        return correct;
    }
}
