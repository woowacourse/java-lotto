package domain;

/**
 * 당첨 번호를 담당하는 객체
 */
public class WinningLotto {
    private final Lotto lotto;
    private final Number bonusNumber;

    private WinningLotto(Lotto lotto, Number bonusNumber) {
        this.lotto = lotto;
        this.bonusNumber = bonusNumber;
    }

    public static WinningLotto of(Lotto lotto, Number bonusNumber) {
        return new WinningLotto(lotto, bonusNumber);
    }

    public Rank match(Lotto userLotto) {
        int countOfMatch = lotto.countEqualNumbers(userLotto);
        return Rank.valueOf(countOfMatch, false);
    }
}
