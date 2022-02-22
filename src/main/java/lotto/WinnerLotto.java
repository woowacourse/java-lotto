package lotto;

public class WinnerLotto {

    private final Lotto winner;
    private final Number bonus;

    public WinnerLotto(Lotto winner, Number bonus) {
        this.winner = winner;
        this.bonus = bonus;
    }

    public int countMatchNumbers(Lotto lotto) {
        return winner.countMatchNumbers(lotto);
    }
}
