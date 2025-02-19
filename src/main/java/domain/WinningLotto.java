package domain;

import static error.ErrorMessage.WINNING_AND_BONUS_NUMBER_DUPLICATE;

import java.util.List;

public class WinningLotto {

    private final Lotto basicLotto;
    private final LottoNumber bonusNumber;

    public WinningLotto(Lotto basicLotto, LottoNumber bonusNumber) {
        validateDuplicatedBonusNumber(basicLotto, bonusNumber);
        this.basicLotto = basicLotto;
        this.bonusNumber = bonusNumber;
    }

    public int getMatchCount(List<LottoNumber> numbers) {
        return basicLotto.countMatches(numbers);
    }

    public boolean isBonusMatched(List<LottoNumber> numbers) {
        return numbers.contains(bonusNumber);
    }

    private static void validateDuplicatedBonusNumber(Lotto basicLotto, LottoNumber bonusNumber) {
        if (basicLotto.isBonusMatched(bonusNumber)) {
            throw new IllegalArgumentException(WINNING_AND_BONUS_NUMBER_DUPLICATE.getMessage());
        }
    }
}
