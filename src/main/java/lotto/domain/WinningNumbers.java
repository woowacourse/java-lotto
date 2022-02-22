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

    public Ranking calculatePrize(LottoNumbers otherLottoNumbers) {
        int cnt = lottoNumbers.calculateSameCount(otherLottoNumbers);

        if (cnt == 5 && otherLottoNumbers.isContain(bonusNumber.getNumber())) {
            return Ranking.SECOND;
        }

        if (cnt == 5) {
            return Ranking.THIRD;
        }

        return Ranking.findRankingByCnt(cnt);
    }

}
