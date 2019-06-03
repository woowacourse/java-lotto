package lotto.domain.lotto;

import lotto.domain.InvalidWinLotto;

import java.util.*;
import java.util.stream.Collectors;

public class WinningLotto {
    private Lotto winningLotto;
    private BonusBall bonusBall;

    public WinningLotto(String[] inputWinLottoNumber, String bonusBall) {
        this.winningLotto = invalidWinLottoNumber(inputWinLottoNumber);
        this.bonusBall = new BonusBall(this.winningLotto, bonusBall);
    }

    private Lotto invalidWinLottoNumber(String[] winningLotto) {
        try {
            return new Lotto(
                    Arrays.stream(winningLotto)
                            .map(String::trim)
                            .map(Integer::parseInt)
                            .map(LottoNumber::new)
                            .collect(Collectors.toList()));
        } catch (InputMismatchException e) {
            throw new InvalidWinLotto("정수외의 문자가 입력되었습니다.");
        }
    }

    public Lotto getWinningLotto() {
        return winningLotto;
    }

    public BonusBall getBonusBall() {
        return bonusBall;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WinningLotto winningLotto1 = (WinningLotto) o;
        return Objects.equals(winningLotto, winningLotto1.winningLotto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(winningLotto);
    }
}
