package domain;

public class WinningLotto {

    private Lotto lotto;
    private int bonus;

    public WinningLotto(String lotto, String bonus) {
        this.lotto = new Lotto(lotto);
        this.bonus = this.lotto.validateBonus(bonus);
    }


}
