package lotto.domain.lotto;

import lotto.domain.Rank;

import java.util.List;

import static lotto.view.ErrorMessages.ERROR_BONUS_LOTTO_NUMBER_DUPLICATED;

public class WinningNumbers {
    private LottoLine lottoLine;
    private LottoNumber bonusNumber;

    public WinningNumbers(LottoLine lottoLine, LottoNumber bonusNumber) {
        if (lottoLine.containNumber(bonusNumber)) {
            throw new IllegalArgumentException(ERROR_BONUS_LOTTO_NUMBER_DUPLICATED.getMessage());
        }
        this.lottoLine = lottoLine;
        this.bonusNumber = bonusNumber;
    }

    public Rank matchRank(List<LottoNumber> lottoNumbers) {
        int matchCount = (int) lottoNumbers.stream()
                .filter(lottoLine.getValue()::contains)
                .count();

        boolean hasBonusNumber = lottoNumbers.contains(bonusNumber);
        return Rank.matchRank(matchCount, hasBonusNumber);
    }
}
