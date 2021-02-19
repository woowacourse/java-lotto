package lotto.domain;

public class WinningLotto {

    private final Lotto winningLotto;
    private final LottoNumber bonusNumber;

    public WinningLotto(Lotto lotto, LottoNumber bonusNumber) {
        validateBonusNumber(lotto, bonusNumber);
        this.winningLotto = lotto;
        this.bonusNumber = bonusNumber;
    }

    public WinningLotto(Lotto lotto, int number) {
        this(lotto, new LottoNumber(number));
    }

    public Rank findRank(Lotto lotto) {
        int countOfMatch = winningLotto.countOfMatchNumber(lotto);
        boolean matchBonusNumber = lotto.containNumber(bonusNumber);

        return Rank.rankOf(countOfMatch, matchBonusNumber);
    }

    private void validateBonusNumber(Lotto lotto, LottoNumber bonusNumber) {
        if (lotto.containNumber(bonusNumber)) {
            throw new IllegalArgumentException("보너스 번호는 로또 번호와 중복될 수 없습니다.");
        }
    }
}
