package model;

public class WinningLotto {
    private final Lotto lotto;
    private final Number bonus;

    public WinningLotto(Lotto lotto, Number bonus) {
        this.lotto = lotto;
        this.bonus = bonus;
    }

    public Lotto getLotto() {
        return lotto;
    }

    public Number getBonus() {
        return bonus;
    }
}
