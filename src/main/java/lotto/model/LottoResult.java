package lotto.model;

public enum LottoResult {
    THREE(3, 5_000),
    FOUR(4, 50_000),
    FIVE(5, 150_000),
    FIVE_BONUS(5, 30_000_000),
    SIX(6, 2_000_000_000);

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
