package domain.lotto.lottoresult;

import domain.lotto.LottoNumber;
import domain.lotto.LottoNumberFactory;
import domain.lotto.LottoNumbers;
import domain.lotto.LottoNumbersFactory;

import java.util.List;
import java.util.Objects;

public class LottoWinner {
    private static final String ERROR_NULL_MESSAGE = "null값이 입력되었습니다.";
    private static final String ERROR_BONUS_MESSAGE = "보너스 숫자가 중복되었습니다.";

    private LottoNumbers lottoNumbers;
    private LottoNumber bonus;

    private LottoWinner(LottoNumbers lottoNumbers, LottoNumber bonus) {
        validateNullValue(lottoNumbers, bonus);
        validateBonus(lottoNumbers, bonus);
        this.lottoNumbers = lottoNumbers;
        this.bonus = bonus;
    }

    public static LottoWinner create(List<Integer> winnerNumbers, int bonusNumber) {
        return new LottoWinner(LottoNumbersFactory.createLottoNumbers(winnerNumbers), LottoNumberFactory.getInstance(bonusNumber));
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
        int result = lottoNumbers.calculateMatchNumber(this.lottoNumbers);
        return LottoRank.calculateRank(result, lottoNumbers.contains(bonus));
    }
}
