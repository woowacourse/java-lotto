package domain;

public class WinningLotto extends Lotto{

    private int bonus;

    public WinningLotto(String lotto, String bonus) {
        super(lotto);
        this.bonus = validateBonus(bonus);
    }

}
