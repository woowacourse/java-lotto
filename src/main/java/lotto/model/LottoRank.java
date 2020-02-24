package lotto.model;

public enum LottoRank {
    FIFTH(3, 5000),
    FOURTH(4, 50000),
    THIRD(5, 150000),
    SECOND(5, 30000000),
    FIRST(6, 2000000000);

    private final int rank;
    private final int prize;

    LottoRank(int correct, int prize) {
        this.rank = correct;
        this.prize = prize;
    }

    public double prizeResult(int count) {
        return count * this.prize;
    }

    public int getPrize() {
        return prize;
    }

    public int getRank() {
        return rank;
    }

    public static boolean checkNoPrize(int count){
        return count >= FIFTH.rank;
    }

    public static boolean checkThirdWinner(int count) {
        return count == THIRD.rank;
    }
}
