package domain;

public class WinningLotto {

    private final Lotto lotto;
    private final Number bonusNumber;

    public WinningLotto(Lotto lotto, Number bonusNumber) {
        validateDuplicatedNumber(lotto, bonusNumber);
        this.lotto = lotto;
        this.bonusNumber = bonusNumber;
    }

    private void validateDuplicatedNumber(Lotto lotto, Number bonusNumber) {
        if (lotto.contains(bonusNumber)) {
            throw new IllegalArgumentException("보너스 볼 번호는 당첨 번호와 중복될 수 없습니다.");
        }
    }

    public Rank calculateRank(Lotto purchasedlotto) {
        int matchCount = lotto.calculateMatchCount(purchasedlotto);
        boolean isMatchBonusNumber = containsBonusNumber(purchasedlotto);
        return Rank.findRank(matchCount, isMatchBonusNumber);
    }

    public boolean containsBonusNumber(Lotto lotto) {
        return lotto.contains(bonusNumber);
    }
}
