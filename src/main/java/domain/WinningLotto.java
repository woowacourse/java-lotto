package domain;

public class WinningLotto {

    private final Lotto lotto;
    private final int bonusBall;

    public WinningLotto(final Lotto lotto, int bonusBall) {
        this.lotto = lotto;
        this.bonusBall = bonusBall;
    }
}
