package lottogame.domain.lotto;

import lottogame.utils.InvalidWinningLottoException;
import lottogame.utils.RedundantNumbersException;

import java.util.*;
import java.util.regex.Pattern;

public class WinningLotto {
    private static final Pattern BONUS_NUMBER_PATTERN = Pattern.compile("^[0-9]*$");
    private final Lotto lotto;
    private final LottoNumber bonusNumber;

    public WinningLotto(String numbers, String bonusNumber) {
        Lotto lotto = Lotto.of(numbers);
        validateBonusNumber(bonusNumber);
        this.bonusNumber = new LottoNumber(Integer.parseInt(bonusNumber));
        isDuplicate(lotto, this.bonusNumber);
        this.lotto = lotto;
    }

    private void validateBonusNumber(String bonusNumber) {
        if (!BONUS_NUMBER_PATTERN.matcher(bonusNumber).matches()) {
            throw new InvalidWinningLottoException();
        }
    }

    private void isDuplicate(Lotto lotto, LottoNumber bonusNumber) {
        if (lotto.contains(bonusNumber)) {
            throw new RedundantNumbersException();
        }
    }

    public LottoNumber getBonusBall() {
        return this.bonusNumber;
    }

    public Lotto values() {
        return lotto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WinningLotto that = (WinningLotto) o;
        return Objects.equals(lotto, that.lotto) && Objects.equals(bonusNumber, that.bonusNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lotto, bonusNumber);
    }
}
