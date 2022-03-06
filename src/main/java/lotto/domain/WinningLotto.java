package lotto.domain;

import static lotto.constant.ErrorMessage.ERROR_WINNING_LOTTO_DUPLICATE;

import java.util.List;

public class WinningLotto {

    private final Lotto winningNumbers;
    private final LottoNumber bonusNumber;

    public WinningLotto(final List<Integer> winningNumbers, final int bonusNumber) {
        final LottoGenerator lottoGenerator = new FixedLottoGenerator(winningNumbers);
        this.winningNumbers = new Lotto(lottoGenerator);
        this.bonusNumber = LottoNumber.valueOf(bonusNumber);

        validateDuplication(this.winningNumbers, this.bonusNumber);
    }

    private void validateDuplication(final Lotto winningNumber, final LottoNumber bonusNumber) {
        if (winningNumber.contains(bonusNumber)) {
            throw new IllegalArgumentException(ERROR_WINNING_LOTTO_DUPLICATE.message());
        }
    }

    public int countWinningMatched(final Lotto lotto) {
        return winningNumbers.countMatchedNumbers(lotto);
    }

    public boolean isBonusMatched(final Lotto lotto) {
        return lotto.contains(bonusNumber);
    }
}
