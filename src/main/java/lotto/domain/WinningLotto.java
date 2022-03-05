package lotto.domain;

public class WinningLotto {
    private static final String ERROR_NULL = "올바른 값을 입력해주세요!";
    private static final String ERROR_DUPLICATED_NUMBER = "번호가 중복됩니다!";
    private static final int CHECK_BONUS_COUNT = 5;

    private final Lotto winningNumbers;
    private final Ball bonusBall;

    public WinningLotto(final Lotto winningNumbers, final Ball bonusBall) {
        validateWinningLotto(winningNumbers, bonusBall);
        this.winningNumbers = winningNumbers;
        this.bonusBall = bonusBall;
    }

    public void match(final Lottos lottos, final LottoResult lottoResult) {
        for (Lotto lotto : lottos.getLottos()) {
            int matchingCount = getMatchingCount(lotto);
            boolean bonus = isBonus(lotto, matchingCount);

            lottoResult.increaseRankCount(Rank.getRank(matchingCount, bonus));
        }
    }

    private void validateWinningLotto(final Lotto winningNumbers, final Ball bonusBall) {
        validateNull(winningNumbers, bonusBall);
        validateDuplicatedNumber(winningNumbers, bonusBall);
    }

    private void validateNull(final Lotto lotto, final Ball ball) {
        if (lotto == null || ball == null) {
            throw new IllegalArgumentException(ERROR_NULL);
        }
    }

    private void validateDuplicatedNumber(final Lotto lotto, final Ball ball) {
        if (lotto.contains(ball)) {
            throw new IllegalArgumentException(ERROR_DUPLICATED_NUMBER);
        }
    }

    private int getMatchingCount(final Lotto otherLotto) {
        return (int) winningNumbers.getLottoBalls().stream()
                .filter(otherLotto::contains)
                .count();
    }

    private boolean isBonus(final Lotto otherLotto, final int matchingCount) {
        return matchingCount == CHECK_BONUS_COUNT && otherLotto.contains(bonusBall);
    }
}
