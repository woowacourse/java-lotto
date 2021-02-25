package lottogame.domain.lotto;

import lottogame.domain.stats.LottoResults;

import java.util.Objects;

public class LottoGame {
    private Lottos lottos;
    private WinningLotto winningLotto;

    public LottoGame(Lottos lottos, WinningLotto winningLotto) {
        this.lottos = lottos;
        this.winningLotto = winningLotto;
    }

    public LottoResults results() {
        return lottos.matchLottos(winningLotto);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoGame lottoGame = (LottoGame) o;
        return Objects.equals(lottos, lottoGame.lottos) && Objects.equals(winningLotto, lottoGame.winningLotto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottos, winningLotto);
    }
}
