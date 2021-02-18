package domain.lotto;

public class WinningLotto {
    private final LottoNumbers lottoNumbers;
    private final BonusNumber bonusNumber;

    public WinningLotto(final LottoNumbers lottoNumbers, final BonusNumber bonusNumber) {
        validateWinningLotto(lottoNumbers, bonusNumber);
        this.lottoNumbers = lottoNumbers;
        this.bonusNumber = bonusNumber;
    }

    private void validateWinningLotto(final LottoNumbers lottoNumbers, final BonusNumber bonusNumber) {
        if (lottoNumbers.containNumber(bonusNumber)) {
            throw new IllegalArgumentException("중복된 값이 있습니다. 다시 입력해주세요 ");
        }
    }
}
