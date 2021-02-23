package lotto.domain.lotto;

import lotto.domain.Rank;

import java.util.List;

public class WinningNumbers {
    private static final String ERROR_BONUS_LOTTO_NUMBER_DUPLICATED = "[Error] 보너스 번호는 지난 당첨번호와 중복 될 수 없습니다.";
    private LottoLine lottoLine;
    private LottoNumber bonusNumber;

    public WinningNumbers(LottoLine lottoLine, LottoNumber bonusNumber) {
        if (lottoLine.containNumber(bonusNumber)) {
            throw new IllegalArgumentException(ERROR_BONUS_LOTTO_NUMBER_DUPLICATED);
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
