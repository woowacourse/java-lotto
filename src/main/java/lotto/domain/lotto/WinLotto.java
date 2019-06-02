package lotto.domain.lotto;

import lotto.domain.InvalidWinLotto;

import java.util.*;
import java.util.stream.Collectors;

public class WinLotto {
    private Lotto winLotto;

    public WinLotto(String[] inputWinLottoNumber) {
        this.winLotto = invalidWinLottoNumber(inputWinLottoNumber);
    }

    private Lotto invalidWinLottoNumber(String[] winLotto) {
        try {
            return new Lotto(
                    Arrays.stream(winLotto)
                            .map(String::trim)
                            .map(Integer::parseInt)
                            .map(LottoNumber::new)
                            .collect(Collectors.toList()));
        } catch (InputMismatchException e) {
            throw new InvalidWinLotto("정수외의 문자가 입력되었습니다.");
        }
    }

    public Lotto getWinLotto() {
        return winLotto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WinLotto winLotto1 = (WinLotto) o;
        return Objects.equals(winLotto, winLotto1.winLotto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(winLotto);
    }
}
