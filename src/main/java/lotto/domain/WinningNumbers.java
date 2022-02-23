package lotto.domain;

public class WinningNumbers {
    private LottoNumbers lottoNumbers;
    private LottoNumber bonusNumber;

    public WinningNumbers(LottoNumbers lottoNumbers, LottoNumber bonusNumber) {
        validateDuplicateNumber(lottoNumbers, bonusNumber);
        this.lottoNumbers = lottoNumbers;
        this.bonusNumber = bonusNumber;
    }

    private void validateDuplicateNumber(LottoNumbers lottoNumbers, LottoNumber bonusNumber) {
        if (lottoNumbers.isContain(bonusNumber)) {
            throw new IllegalArgumentException();
        }
    }

    public Ranking calculatePrize(LottoNumbers otherLottoNumbers) {
        int cnt = lottoNumbers.calculateSameCount(otherLottoNumbers);

        if (cnt == 5 && otherLottoNumbers.isContain(bonusNumber)) {
            return Ranking.SECOND;
        }

        if (cnt == 5) {
            return Ranking.THIRD;
        }

        return Ranking.findRankingByCnt(cnt);
    }

}
