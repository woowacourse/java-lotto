package lotto.domain;

import java.util.Optional;

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

    public Optional<Ranking> calculateRanking(LottoNumbers otherLottoNumbers) {
        int cnt = lottoNumbers.calculateSameCount(otherLottoNumbers);
        return Ranking.findRanking(cnt, otherLottoNumbers.isContain(bonusNumber));
    }


}
