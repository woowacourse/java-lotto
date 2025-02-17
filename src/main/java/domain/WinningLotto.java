package domain;

public class WinningLotto {

    private final Lotto lotto;
    private final LottoNumber bonusNumber;

    public WinningLotto(Lotto lotto, LottoNumber bonusNumber) {
        validateDuplicatedNumber(lotto, bonusNumber);
        this.lotto = lotto;
        this.bonusNumber = bonusNumber;
    }

    public Rank calculateRank(Lotto lotto) {
        int matchCount = this.lotto.calculateMatchCount(lotto);
        boolean isMatchBonusNumber = lotto.contains(bonusNumber);
        return Rank.findRank(matchCount, isMatchBonusNumber);
    }

    private void validateDuplicatedNumber(Lotto lotto, LottoNumber bonusNumber) {
        if (lotto.contains(bonusNumber)) {
            throw new IllegalArgumentException("보너스 볼 번호는 당첨 번호와 중복될 수 없습니다.");
        }
    }
}
