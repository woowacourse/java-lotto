package lotto.model;

public enum LottoRank {
    FIFTH(3, 5000, "FIFTH"),
    FOURTH(4, 50000, "FOURTH"),
    THIRD(5, 150000, "THIRD"),
    SECOND(5, 30000000, "SECOND"),
    FIRST(6, 2000000000, "FIRST");

    private final int prize;
    private final int correct;
    private final String rank;

    LottoRank(int correct, int prize, String rank) {
        this.correct = correct;
        this.prize = prize;
        this.rank = rank;
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

    public String getRank() {
        return rank;
    }

    public static boolean isCorrectNumberOverThree(int count){
        return count >= FIFTH.correct;
    }

    public static boolean isCorrectNumberFive(int count) {
        return count == THIRD.correct;
    }
}
