package domain;

public class WinningLotto {

    private final Lotto winningLotto;
    private final LottoNumber bonusNumber;

    public WinningLotto(Lotto winningLotto, LottoNumber bonusNumber) {
        validateNull(winningLotto, bonusNumber);
        validateDuplicate(winningLotto, bonusNumber);
        this.winningLotto = winningLotto;
        this.bonusNumber = bonusNumber;
    }

    private void validateNull(Lotto winningLotto, LottoNumber bonusNumber) {
        if (winningLotto == null) {
            throw new NullPointerException("WinningLotto 생성시 당첨 로또가 null 일 수 없습니다.");
        }
        if (bonusNumber == null) {
            throw new NullPointerException("WinningLotto 생성시 보너스 번호가 null 일 수 없습니다.");
        }
    }

    private void validateDuplicate(Lotto winningLotto, LottoNumber bonusNumber) {
        if (winningLotto.containsNumber(bonusNumber)) {
            throw new IllegalArgumentException("보너스 볼은 당첨번호와 중복될 수 없습니다.");
        }
    }

    public LottoReward match(Lotto lotto) {
        int matchCount = winningLotto.calculateSameNumber(lotto);
        boolean hasBonus = lotto.containsNumber(bonusNumber);
        return LottoReward.find(matchCount, hasBonus);
    }
}
