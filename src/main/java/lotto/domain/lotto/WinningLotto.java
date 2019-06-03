package lotto.domain.lotto;

import lotto.domain.InvalidWinnigLotto;

import java.util.*;
import java.util.stream.Collectors;

public class WinningLotto extends Lotto {

    private BonusBall bonusBall;

    public WinningLotto(String[] inputWinLottoNumber, String bonusBall) {
        this.lottoNumbers = invalidWinLottoNumber(inputWinLottoNumber);
        this.bonusBall = new BonusBall(this.lottoNumbers, bonusBall);
    }

    private List<LottoNumber> invalidWinLottoNumber(String[] winningLotto) {
        try {
            return invalidNumberOfLotto(Arrays.stream(winningLotto)
                            .map(String::trim)
                            .map(Integer::parseInt)
                            .map(LottoNumber::new)
                            .collect(Collectors.toList()));
        } catch (NumberFormatException e) {
            throw new InvalidWinnigLotto("정수외의 문자가 입력되었습니다.");
        } catch (InputMismatchException e) {
            throw new InvalidWinnigLotto("정수외의 수가 입력되었습니다.");
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
