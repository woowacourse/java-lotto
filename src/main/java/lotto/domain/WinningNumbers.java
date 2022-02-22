package lotto.domain;

public class WinningNumbers {
    private LottoNumbers lottoNumbers;
    private BonusNumber bonusNumber;

    public WinningNumbers(LottoNumbers lottoNumbers, BonusNumber bonusNumber) {
        validateDuplicateNumber(lottoNumbers, bonusNumber);
        this.lottoNumbers = lottoNumbers;
        this.bonusNumber = bonusNumber;
    }

    private void validateDuplicateNumber(LottoNumbers lottoNumbers, BonusNumber bonusNumber) {
        if (lottoNumbers.isContain(bonusNumber.getNumber())) {
            throw new IllegalArgumentException();
        }
    }

}
