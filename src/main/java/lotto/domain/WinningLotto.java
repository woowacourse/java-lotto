package lotto.domain;

import java.util.Objects;

public class WinningLotto {
    private final Lotto winningLotto;

    WinningLotto(final Lotto winningLotto) {
        this.winningLotto = winningLotto;
    }

    int countOfMatchLottoNumber(Lotto lotto) {
        int countOfMatch = 0;
        for (LottoNumber lottoNumber : lotto.getNumbers()) {
            countOfMatch += winningLotto.hasNumber(lottoNumber);
        }

        return countOfMatch;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final WinningLotto that = (WinningLotto) o;
        return Objects.equals(winningLotto, that.winningLotto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(winningLotto);
    }
}
