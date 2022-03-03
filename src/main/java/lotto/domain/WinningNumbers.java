package lotto.domain;

import java.util.List;
import lotto.exception.BonusNumberException;
import lotto.generator.LottoGenerator;

public class WinningNumbers {

    private final Lotto winningLotto;
    private final LottoNumber bonusNumber;

    public WinningNumbers(List<Integer> winningNumbersInput, int bonusNumberInput) {
        this.winningLotto = LottoGenerator.generateLottoByManual(winningNumbersInput);
        LottoNumber bonusNumber = LottoNumber.getByNumber(bonusNumberInput);
        checkDuplication(winningLotto, bonusNumber);
        this.bonusNumber = bonusNumber;
    }

    private static void checkDuplication(Lotto winningNumbers, LottoNumber lottoNumber) {
        if (winningNumbers.isContain(lottoNumber)) {
            throw new BonusNumberException(BonusNumberException.BONUS_NUMBER_DUPLICATION_ERROR_MESSAGE);
        }
    }

    public int getWinningLottoMatchCount(List<LottoNumber> lotto) {
        return winningLotto.getMatchCount(lotto);
    }

    public boolean isBonusNumberContainedAt(List<LottoNumber> lotto) {
        return lotto.contains(bonusNumber);
    }
}
