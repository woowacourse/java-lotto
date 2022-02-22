package lotto;

public class WinnerLotto {

    private final Lotto winner;
    private final Number bonus;

    public WinnerLotto(Lotto winner, Number bonus) {
        this.winner = winner;
        this.bonus = bonus;
    }

    public Rank findRank(Lotto lotto) {
        if (winner.countMatchNumbers(lotto) == 6) {
            return Rank.FIRST;
        }

        if (winner.countMatchNumbers(lotto) == 2) {
            if (lotto.containsNumber(bonus)) {
                return Rank.FIFTH;
            }
        }

        if (winner.countMatchNumbers(lotto) == 3) {
            if (lotto.containsNumber(bonus)) {
                return Rank.FOURTH;
            }
            return Rank.FIFTH;
        }

        if (winner.countMatchNumbers(lotto) == 4) {
            if (lotto.containsNumber(bonus)) {
                return Rank.THIRD;
            }
            return Rank.FOURTH;
        }

        if (winner.countMatchNumbers(lotto) == 5) {
            if (lotto.containsNumber(bonus)) {
                return Rank.SECOND;
            }
            return Rank.THIRD;
        }
        return Rank.NONE;
    }
}
