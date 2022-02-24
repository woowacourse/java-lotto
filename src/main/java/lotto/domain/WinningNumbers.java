package lotto.domain;

import java.util.Optional;

public class WinningNumbers {
    private static final String DUPLICATE_ERROR = "보너스 번호는 당첨 번호와 중복될 수 없습니다";

    private final LottoNumbers lottoNumbers;
    private final LottoNumber bonusNumber;

    public WinningNumbers(LottoNumbers lottoNumbers, LottoNumber bonusNumber) {
        validateDuplicateNumber(lottoNumbers, bonusNumber);
        this.lottoNumbers = lottoNumbers;
        this.bonusNumber = bonusNumber;
    }

    public Optional<Ranking> calculateRanking(LottoNumbers otherLottoNumbers) {
        int cnt = lottoNumbers.calculateSameCount(otherLottoNumbers);
        return Ranking.findRanking(cnt, otherLottoNumbers.isContain(bonusNumber));
    }

    private void validateDuplicateNumber(LottoNumbers lottoNumbers, LottoNumber bonusNumber) {
        if (lottoNumbers.isContain(bonusNumber)) {
            throw new IllegalArgumentException(DUPLICATE_ERROR);
        }
    }
}
