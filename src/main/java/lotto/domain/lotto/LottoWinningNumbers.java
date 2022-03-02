package lotto.domain.lotto;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lotto.exception.InvalidException;

public class LottoWinningNumbers {

    private final Lotto winningLotto;
    private final LottoNumber bonusNumber;

    public LottoWinningNumbers(final Lotto winningLotto, final LottoNumber bonusNumber) {
        checkNumbers(winningLotto, bonusNumber);
        this.winningLotto = winningLotto;
        this.bonusNumber = bonusNumber;
    }

    private void checkNumbers(Lotto winningLotto, LottoNumber bonusNumber) {
        checkNullEmpty(winningLotto, bonusNumber);
        checkDuplicateBonusNumber(winningLotto, bonusNumber);
    }

    private void checkNullEmpty(Lotto winningLotto, LottoNumber bonusNumber) {
        if (winningLotto == null || bonusNumber == null){
            throw new IllegalArgumentException(InvalidException.ERROR_CREATE_LOTTO);
        }
    }

    private void checkDuplicateBonusNumber(Lotto winningLotto, LottoNumber bonusNumber) {
        if (winningLotto.contains(bonusNumber.getLottoNumber())){
            throw new IllegalArgumentException(InvalidException.ERROR_DUPLICATE_BONUS_NUMBER);
        }
    }

    public Lotto getWinningLotto() {
        return winningLotto;
    }

    public int getBonusNumber() {
        return bonusNumber.getLottoNumber();
    }
}
