package model;

public class WinningLottoNumbers {
    private final Lotto lotto;
    private final LottoNumber bonusNumber;

    public WinningLottoNumbers(Lotto lotto, LottoNumber bonusNumber) {
        if (lotto.contains(bonusNumber)) {
            throw new IllegalArgumentException("중복된 로또 번호는 입력할 수 없습니다.");
        }
        this.lotto = lotto;
        this.bonusNumber = bonusNumber;
    }

    public LottoRank getRankBy(Lotto lotto) {
        int count = getMatchedCountAboutWinningNumbers(lotto);
        boolean bonusMatch = isBonusMatch(lotto);
        return LottoRank.of(count, bonusMatch);
    }

    private int getMatchedCountAboutWinningNumbers(Lotto lotto) {
        return this.lotto.getMatchedNumberCountWith(lotto);
    }

    private boolean isBonusMatch(Lotto lotto) {
        return lotto.contains(bonusNumber);
    }
}
