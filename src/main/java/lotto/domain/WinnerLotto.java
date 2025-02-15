package lotto.domain;

public class WinnerLotto {
    private final Lotto winnerNumbers;
    private final LottoNumber bonusNumber;

    public WinnerLotto(Lotto winnerNumbers, LottoNumber bonusNumber) {
        this.winnerNumbers = winnerNumbers;
        this.bonusNumber = bonusNumber;
    }


    public static void validateBonusNumbers(Lotto winnerNumbers, LottoNumber bonusNumber) {
        if (winnerNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("보너스 넘버가 당첨 번호에 중복됩니다.");
        }
    }

    public long getMatchCount(Lotto lotto) {
        return lotto.getMatchCount(winnerNumbers);
    }


    public boolean hasBonus(Lotto lotto) {
        return lotto.hasBonusNumber(bonusNumber);
    }
}
