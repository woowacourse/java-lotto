package lotto.domain;

public class WinningLotto {
    private static final String ERROR_DUPLICATED_NUMBER = "번호가 중복됩니다!";
    private static final int CHECK_BONUS_COUNT = 5;

    private final Lotto winningLotto;
    private final LottoNumber bonusBall;

    public WinningLotto(Lotto winningLotto, LottoNumber bonusBall) {
        checkDuplicatedNumber(winningLotto, bonusBall);
        this.winningLotto = winningLotto;
        this.bonusBall = bonusBall;
    }

    private void checkDuplicatedNumber(Lotto lotto, LottoNumber ball) {
        if (lotto.contains(ball)) {
            throw new IllegalArgumentException(ERROR_DUPLICATED_NUMBER);
        }
    }

    public Rank getLottoRank(Lotto lotto) {
        int matchingCount = lotto.getMatchingCount(winningLotto);
        boolean isBonus = (matchingCount == CHECK_BONUS_COUNT) && (lotto.contains(bonusBall));
        return Rank.of(matchingCount, isBonus);
    }
}
