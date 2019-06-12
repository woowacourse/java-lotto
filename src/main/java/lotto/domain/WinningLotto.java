package lotto.domain;

import lotto.domain.exception.InvalidWinnigLottoException;
import lotto.util.AscendingNumber;

import java.util.*;

import static java.util.Collections.sort;
import static java.util.stream.Collectors.*;
import static lotto.view.InputView.SPLIT_REGEX;

public class WinningLotto extends Lotto {

    private BonusBall bonusBall;

    public WinningLotto(String inputWinLottoNumber, String bonusBall) {
        this.lottoNumbers = invalidWinningLottoNumbers(inputWinLottoNumber);
        sort(lottoNumbers, new AscendingNumber());
        this.bonusBall = new BonusBall(this.lottoNumbers, bonusBall);
    }

    private List<LottoNumber> invalidWinningLottoNumbers(String winningLotto) {
        try {
            return invalidNumberOfLotto(Arrays.stream(winningLotto.split(SPLIT_REGEX))
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .map(LottoNumber::new)
                    .collect(toList()));
        } catch (NumberFormatException e) {
            throw new InvalidWinnigLottoException(e);
        } catch (InputMismatchException e) {
            throw new InvalidWinnigLottoException(e);
        }
    }

    public List<LottoNumber> getWinningLotto() {
        return lottoNumbers;
    }

    public BonusBall getBonusBall() {
        return bonusBall;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        WinningLotto that = (WinningLotto) o;
        return Objects.equals(bonusBall, that.bonusBall);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), bonusBall);
    }
}
