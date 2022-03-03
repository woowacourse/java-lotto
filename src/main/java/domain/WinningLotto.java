package domain;

public class WinningLotto {

    private final LottoNumbers lottoNumbers;
    private final LottoNumber bonusNumber;

    public WinningLotto(LottoNumbers lottoNumbers, LottoNumber bonusNumber) {
        this.lottoNumbers = lottoNumbers;
        this.bonusNumber = bonusNumber;
    }

    public LottoRank rank(LottoNumbers lottoNumbers) {
        int count = this.lottoNumbers.countDuplicateNumbers(lottoNumbers);
        boolean hasBonus = this.lottoNumbers.contains(bonusNumber);

        return LottoRank.valueOf(count, hasBonus);
    }
}
