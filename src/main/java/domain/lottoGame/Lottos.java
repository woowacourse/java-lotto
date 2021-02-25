package domain.lottoGame;

import domain.Money;

import java.util.*;

public class Lottos {

    private final List<Lotto> lottos;

    public Lottos(final List<Lotto> lottos) {
        this.lottos = new ArrayList<>(lottos);
    }

    public LottoWinningTable makeWinningTable(WinningLotto winningLotto) {
        Map<LottoRank, Long> results = new HashMap<>();

        for (LottoRank rank : LottoRank.values()) {
            results.put(rank, 0L);
        }

        for (Lotto lotto : lottos) {
            LottoRank winningRank = LottoRank.valueOf(winningLotto, lotto);
            results.put(winningRank, results.get(winningRank) + 1);
        }
        return new LottoWinningTable(results);
    }

    public List<Lotto> getLottos() {
        return new ArrayList<>(lottos);
    }

    public int getNumberOfLotto() {
        return lottos.size();
    }

    public Money getPurchasedAmount() {
        return Lotto.getPurchasedAmount(lottos.size());
    }
}