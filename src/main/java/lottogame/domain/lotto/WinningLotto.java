package lottogame.domain.lotto;

import lottogame.utils.DuplicateLottoNumberException;

import java.util.*;

public class WinningLotto {
    private final Lotto lotto;
    private final LottoNumber bonusBall;

    public WinningLotto(List<Integer> numbers, int bonusBall) {
        lotto = new Lotto(new LottoNumber(numbers));
        this.bonusBall = new LottoNumber(bonusBall);
        validate(numbers, bonusBall);
    }

    private void validate(List<Integer> numbers, int bonusBall) {
        if (numbers.contains(bonusBall)) {
            throw new DuplicateLottoNumberException();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WinningLotto that = (WinningLotto) o;
        return bonusBall == that.bonusBall && Objects.equals(lotto, that.lotto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lotto, bonusBall);
    }
}
