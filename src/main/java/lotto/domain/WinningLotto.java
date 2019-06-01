package lotto.domain;

import java.util.Objects;

public class WinningLotto {
    private final Lotto winningLotto;

    public WinningLotto(String input) {
        LottoManualGenerator lottoManualGenerator = new LottoManualGenerator(input);
        this.winningLotto = lottoManualGenerator.generate();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WinningLotto that = (WinningLotto) o;
        return Objects.equals(winningLotto, that.winningLotto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(winningLotto);
    }
}
