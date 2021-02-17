package domain;

public class WinningLotto {

    private final Lotto lotto;
    private final LottoNumber bonusBall;

    public WinningLotto(Lotto lotto, LottoNumber bonusBall) {
        this.lotto = lotto;
        this.bonusBall = bonusBall;
    }
}
