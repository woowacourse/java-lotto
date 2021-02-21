package domain;

import java.util.*;

public class Lottos {

    private final List<Lotto> lottos;

    public Lottos(final List<Lotto> lottos) {
        this.lottos = new ArrayList<>(lottos);
    }

    public LottoResults getLottoResults(WinningLotto winningLotto) {
        Map<LottoRank, Long> results = new HashMap<>();

        for (LottoRank rank : LottoRank.values()) {
            results.put(rank, 0L);
        }

        for (Lotto lotto : lottos) {
            LottoRank winningRank = LottoRank.valueOf(winningLotto, lotto);
            results.put(winningRank, results.get(winningRank) + 1);
        }
        return new LottoResults(results);
    }

    public List<Lotto> getLottos() {
        return new ArrayList<>(lottos);
    }
}