package domain;

import java.util.Objects;

public class LottoWinner {
    private static final String ERROR_NULL_MESSAGE = "null값이 입력되었습니다.";
    private static final String ERROR_BONUS_MESSAGE = "보너스 숫자가 중복되었습니다.";

    private LottoNumbers lottoNumbers;
    private LottoNumber bonus;

    public LottoWinner(LottoNumbers lottoNumbers, LottoNumber bonus) {
        validateNullValue(lottoNumbers, bonus);
        validateBonus(lottoNumbers, bonus);
        this.lottoNumbers = lottoNumbers;
        this.bonus = bonus;
    }

    private void validateBonus(LottoNumbers lottoNumbers, LottoNumber bonus) {
        if (lottoNumbers.contains(bonus)) {
            throw new IllegalArgumentException(ERROR_BONUS_MESSAGE);
        }
    }

    private void validateNullValue(LottoNumbers lottoNumbers, LottoNumber bonus) {
        if (Objects.isNull(lottoNumbers) || Objects.isNull(bonus)) {
            throw new IllegalArgumentException(ERROR_NULL_MESSAGE);
        }
    }

    public LottoRank createRank(LottoNumbers lottoNumbers) {
        int result = this.lottoNumbers.calculateMatchNumber(lottoNumbers);
        return LottoRank.calculateRank(result, lottoNumbers.contains(bonus));
    }
}
