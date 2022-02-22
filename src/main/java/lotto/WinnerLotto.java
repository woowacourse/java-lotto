package lotto;

public class WinnerLotto {

    private final Lotto winner;
    private final Number bonus;

    public WinnerLotto(Lotto winner, Number bonus) {
        this.winner = winner;
        this.bonus = bonus;
    }

    public Rank findRank(Lotto lotto) {
        return Rank.find(winner.countMatchNumbers(lotto), lotto.containsNumber(bonus));
    }
}
