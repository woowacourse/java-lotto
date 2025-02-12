package model;

public class WinningLotto {

    private Lotto lotto;
    private int bonusNumber;

    public WinningLotto(String input, int bonusNumber) {
        lotto = new Lotto(input);
        this.bonusNumber = bonusNumber;
    }

}
