package domain;

public class WinningLotto {
    private static final String DUPLICATE_BONUS_BALL_MESSAGE = "[ERROR] 보너스 볼은 당첨 번호와 중복될수 없습니다.";

    private final Lotto winningNumbers;
    private final LottoNumber bonusBall;

    public WinningLotto(Lotto winningNumbers, LottoNumber bonusBall) {
        checkBonusBall(winningNumbers, bonusBall);
        this.winningNumbers = winningNumbers;
        this.bonusBall = bonusBall;
    }

    private void checkBonusBall(Lotto winningNumbers, LottoNumber bonusBall) {
        if (winningNumbers.contains(bonusBall)) {
            throw new IllegalArgumentException(DUPLICATE_BONUS_BALL_MESSAGE);
        }
    }

    public boolean isBonusBallMatch(Lotto lotto) {
        return lotto.contains(bonusBall);
    }

    public Rank match(Lotto lotto) {
        int matchCount = winningNumbers.getMatchCount(lotto);
        boolean hasBonusBall = winningNumbers.contains(bonusBall);
        return Rank.valueOf(matchCount, hasBonusBall);
    }

}
