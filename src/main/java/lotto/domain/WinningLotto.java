package lotto.domain;

import java.util.List;
import java.util.Objects;

public class WinningLotto {
    private static final int LOTTO_NUMBER_COUNT = 6;
    private final List<LottoNumber> winningLotto;

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

    public Rank getCountOfMatch(Lotto lotto) {
        int count = 0;
        for (int i = 0; i < LOTTO_NUMBER_COUNT; i++) {
            count += lotto.isContainNumber(this.winningLotto.get(i)) ? 1 : 0;
        }
        Rank rank = Rank.valueOf(count, false);
        return rank;
    }

}
