package lotto.model;

public class WinningLotto {
    private static final String WIN_NUMBER = "당첨 번호";
    private static final String BONUS_BALL = "보너스 볼";
    private static final String COLON = " : ";
    private static final String NEW_LINE = "\n";
    private final Lotto lotto;
    private final int bonusNo;

    public WinningLotto(final Lotto lotto, final int bonusNo) {
        this.lotto = lotto;
        this.bonusNo = bonusNo;
    }

    public Rank match(final Lotto userLotto) {
        int countOfMatch = userLotto.getMatchCount(this.lotto);
        boolean matchBonus = userLotto.hasNumber(this.bonusNo);
        return Rank.valueOf(countOfMatch, matchBonus);
    }

    @Override
    public String toString() {
        return WIN_NUMBER
                + COLON
                + this.lotto.toString()
                + NEW_LINE
                + BONUS_BALL
                + COLON
                + this.bonusNo;
    }
}
