package domain;

public class WinningNumbers {

    private final LottoNumbers lottoNumbers;
    private final LottoNumber bonusNumber;

    public WinningNumbers(LottoNumbers lottoNumbers, LottoNumber bonusNumber) {
        this.lottoNumbers = lottoNumbers;
        this.bonusNumber = bonusNumber;
    }

    public LottoRank rank(LottoNumbers winningNumbers) {
        int count = lottoNumbers.countDuplicateNumbers(winningNumbers);
        boolean hasBonus = lottoNumbers.contains(bonusNumber);

        return LottoRank.valueOf(count, hasBonus);
    }
}
